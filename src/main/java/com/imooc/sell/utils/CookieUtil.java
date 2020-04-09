package com.imooc.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: Cookie工具类
 * @author: zr
 * @date: 2020/4/9 18:17
 */
public class CookieUtil {

    /**
     *
     * @param response response
     * @param name name
     * @param value value
     * @param maxAge maxAge
     */
    public static void setCookie(HttpServletResponse response,
                                 String name,
                                 String value,
                                 int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request,
                                 String name){
        Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)){
                    return cookie;
                }
            }
        }
        return null;
    }

}
