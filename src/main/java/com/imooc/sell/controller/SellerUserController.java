package com.imooc.sell.controller;

import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.constant.CookieConstant;
import com.imooc.sell.constant.RedisConstant;
import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.service.SellerInfoService;
import com.imooc.sell.utils.CookieUtil;
import com.imooc.sell.utils.KeyUtil;
import com.imooc.sell.validateForm.UserInfoForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/9 17:25
 */
@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;


    /**
     *  卖家登陆
     * @param userInfoForm categoryForm
     * @param response HttpServletResponse
     * @param map Map<String,Object>
     * @return ModelAndView
     */
    @PostMapping("/login")
    public ModelAndView login(@Valid UserInfoForm userInfoForm,
                              BindingResult bindingResult,
                              HttpServletResponse response,
                              Map<String,Object> map){

//        //根据openid去和数据库里的数据匹配
//        SellerInfo sellerInfoByOpenid = sellerInfoService.findSellerInfoByOpenid(openid);
//        if(null == sellerInfoByOpenid){
//            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
//            map.put("url","/sell/seller/order/list");
//            return  new ModelAndView("common/error",map);
//        }
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/userLogin");
            return  new ModelAndView("common/error",map);
        }

        SellerInfo userInfo = sellerInfoService.findSellerInfoByUsernameAndPwd(userInfoForm.getUsername(), userInfoForm.getPassword());

        if(null == userInfo){
            map.put("msg", "用户名或者密码不正确");
            map.put("url","/sell/seller/userLogin");
            return  new ModelAndView("common/error",map);
        }
        // 设置token至redis
        String token = UUID.randomUUID().toString();
        //设置token过期时间
        Integer expire = RedisConstant.EXPIRE;
        //  TimeUnit.SECONDS : 设置过期格式
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),userInfo.getOpenid(),expire, TimeUnit.SECONDS);

        // 设置token至cookie
        CookieUtil.setCookie(response, CookieConstant.TOKEN,token,CookieConstant.EXPIRE);

        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");

    }

    /**
     * 注销
     * @param request request
     * @param response response
     * @param map Map<String,Object>
     * @return ModelAndView
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String,Object> map){
        //1.从cookie里查询
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        if(null != cookie){
            //2.清楚redis
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //3.清楚cookie
            CookieUtil.setCookie(response,CookieConstant.TOKEN,null,0);
        }
        map.put("mas","退出成功");
        map.put("url","/sell/seller/userLogin");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/userLogin")
    public ModelAndView userLogin(){
        return new ModelAndView("user/userLogin");
    }

    @RequestMapping("/registered")
    public ModelAndView registered(@Valid UserInfoForm userInfoForm,
                                   BindingResult bindingResult,
                                   Map<String,Object> map){

        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/userLogin");
            return new ModelAndView("common/error",map);
        }
        if(null == userInfoForm.getUsername() || null == userInfoForm.getPassword()){
            //跳转注册页面
            return new ModelAndView("user/register");
        }
        //注册
        SellerInfo sellerInfo = new SellerInfo();
        BeanUtils.copyProperties(userInfoForm,sellerInfo);
        //设置 id
        sellerInfo.setSellerId(KeyUtil.getUniqueKey());
        //设置openid
        sellerInfo.setOpenid("wx"+UUID.randomUUID().toString().replaceAll("-",""));

        sellerInfoService.save(sellerInfo);

        map.put("mas","注册成功");
        map.put("url","/sell/seller/userLogin");
        return new ModelAndView("common/success",map);

    }

}
