package com.imooc.sell.utils;

import com.imooc.sell.VO.ResultVO;

/**
 * @Description: 返回值 工具包
 * @author: zr
 * @date: 2020/4/2 23:53
 */
public class ResultVoUtil {

    public static ResultVO success(Object object){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setMsg(msg);
        resultVO.setCode(code);
        return resultVO;
    }
}
