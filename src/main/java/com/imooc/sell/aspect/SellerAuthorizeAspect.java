package com.imooc.sell.aspect;

import com.imooc.sell.constant.CookieConstant;
import com.imooc.sell.constant.RedisConstant;
import com.imooc.sell.exception.SellerAuthorizeException;
import com.imooc.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/9 19:43
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //("execution(public * com.imooc.sell.controller.Seller*.*(..)) && !execution(public * com.imooc.sell.controller.SellerUserController.*(..))")
    @Pointcut("execution(public * com.imooc.sell.controller.Seller*.*(..)) && (!execution(public * com.imooc.sell.controller.SellerUserController.*(..)))")
    public void verify(){};

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        if(null == cookie){
            log.warn("【登陆校验】Cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        //去查redis
        String tokenValue = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.warn("【登陆校验】redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
