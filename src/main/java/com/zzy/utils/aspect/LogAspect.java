package com.zzy.utils.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzy.entity.SysLog;
import com.zzy.entity.SysUser;
import com.zzy.service.LogService;
import com.zzy.utils.exception.GlobalException;
import com.zzy.utils.util.HttpContextUtil;
import com.zzy.utils.util.IPUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 切面
 * @Author Zzy
 * @Date 2020/12/25
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.zzy.utils.annotation.Log)")
    public  void pointcut(){

    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException {
        Object result=null;
        try{
            result=proceedingJoinPoint.proceed();
        }catch (Throwable throwable){
            throwable.printStackTrace();
            throw new GlobalException(throwable.getMessage());
        }
        SysUser sysUser= (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(sysUser!=null){
            long beginTime=System.currentTimeMillis();
            //获取Request请求
            HttpServletRequest request= HttpContextUtil.getHttpServletRequest();
            //设置IP地址
            String ip= IPUtil.getIpAddr(request);
            //记录时间
            long time=System.currentTimeMillis()-beginTime;
            //保存日志
            SysLog log=new SysLog();
            log.setIp(ip);
            log.setTime(time);
            log.setUsername(sysUser.getUsername());
            logService.saveLog(proceedingJoinPoint,log);
        }

        return result;

    }

}
