package com.zzy.utils.handler;

import com.zzy.utils.exception.GlobalException;
import com.zzy.utils.constant.CommonConstant;
import com.zzy.utils.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

/**
 * @Author Zzy
 * @Date 2020/12/27
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e) {
        log.error("内部错误, {}", e.getMessage());
        e.printStackTrace();
        return new Result(e);
    }

    @ExceptionHandler(value = GlobalException.class)
    public Result globalExceptionHandle(GlobalException e) {
        log.error("全局异常, {}", e.getMessage());
        e.printStackTrace();
        return new Result(CommonConstant.ERROR, e.getMsg());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public Result handleUnauthorizedException(UnauthorizedException e) {
        log.error("UnauthorizedException, {}", e.getMessage());
        return new Result(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public Result handleAuthenticationException(AuthenticationException e) {
        log.error("AuthenticationException, {}", e.getMessage());
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e) {
        log.error("AuthorizationException, {}", e.getMessage());
        return new Result(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }
}
