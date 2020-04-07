package com.imooc.sell.utils;

/**
 * @Description: 价格判断
 * @author: zr
 * @date: 2020/4/7 18:26
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01d;

    public static Boolean equals(Double num1,Double num2){
        double result = Math.abs(num1 - num2);
        return result <= MONEY_RANGE;
    }
}
