package com.imooc.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Description: JSON 格式化
 * @author: zr
 * @date: 2020/4/7 16:29
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }


}
