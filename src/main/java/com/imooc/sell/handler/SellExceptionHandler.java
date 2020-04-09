package com.imooc.sell.handler;

import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.exception.SellerAuthorizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 拦截登陆异常
 * @author: zr
 * @date: 2020/4/9 20:40
 */
@ControllerAdvice
@Slf4j
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;


    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        // TODO 拦截登陆异常跳转路径
        log.info("【登陆校验】 登陆异常被拦截 转发地址：{}",projectUrlConfig.getSell().concat("/sell/seller/userLogin"));
        return new ModelAndView("redirect:".concat(projectUrlConfig.getSell()).concat("/sell/seller/userLogin"));
    }
}