package com.imooc.sell.utils;

import com.imooc.sell.enums.CodeEnum;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/8 14:30
 */
public class EnumUtil {
    public static <T extends CodeEnum>T getByCode(Integer code, Class<T> enumClass){
        for(T t: enumClass.getEnumConstants()){
            if(code.equals(t.getCode())){
                return t;
            }
        }

        return null;
    }
}
