package com.zzy.utils.exception;


import lombok.Getter;
import lombok.Setter;

/**全句异常处理
 * @Author Zzy
 * @Date 2020/12/27
 */

public class GlobalException extends RuntimeException{
    @Getter
    @Setter
    private String msg;

    public GlobalException(String message, String msg) {
        super(message);
        this.msg = msg;
    }

    public GlobalException(String message) {
        this.msg=getMsg();
    }
}
