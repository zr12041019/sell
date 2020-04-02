package com.imooc.sell.utils;

import java.util.Random;

/**
 * @Description: 随机数
 * @author: zr
 * @date: 2020/4/3 20:34
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式 时间+随机数
     * @return 主键
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
