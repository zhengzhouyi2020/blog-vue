package com.zzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzy.entity.SysLog;
import com.zzy.utils.util.QueryPage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * @Author Zzy
 * @Date 2020/12/26
 */
public interface LogService extends IService<SysLog> {

    IPage<SysLog> list(SysLog sysLog, QueryPage queryPage);

    void delete(Long id);

    @Async
    void saveLog(ProceedingJoinPoint proceedingJoinPoint,SysLog sysLog) throws JsonProcessingException;
}
