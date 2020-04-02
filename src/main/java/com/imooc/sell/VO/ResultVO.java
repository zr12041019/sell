package com.imooc.sell.VO;

import lombok.Data;

import java.util.List;

/**
 * @Description: http请求返回的最外层对象
 * @author: zr
 * @date: 2020/4/2 22:29
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回的具体内容
     */
    private T data;

}
