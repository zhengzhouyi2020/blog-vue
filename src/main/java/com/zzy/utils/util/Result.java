package com.zzy.utils.util;

import com.zzy.utils.constant.CommonConstant;

import com.zzy.utils.constant.CommonEnum;
import lombok.*;

import java.io.Serializable;

/**
 * @Author Zzy
 * @Date 2020/12/25
 */
@Builder //建造者模式
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Result<T> implements Serializable {


    private int code= CommonConstant.SUCCESS;

    private Object msg="success";

    private T data;

    public Result(T data){
        this.data=data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public Result(CommonEnum enums){
        super();
        this.code=enums.getCode();
        this.msg=enums.getMsg();
    }

    public Result(Throwable e){
        super();
        this.code=CommonConstant.ERROR;
        this.msg=e.getMessage();
    }
}
