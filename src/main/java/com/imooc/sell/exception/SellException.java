package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * @Description: 异常类
 * @author: zr
 * @date: 2020/4/3 20:00
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    /**
     * 项目异常类
     * @param resultEnum ResultEnum
     */
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
