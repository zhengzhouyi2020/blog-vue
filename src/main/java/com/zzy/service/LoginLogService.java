package com.zzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.entity.SysLoginLog;
import com.zzy.utils.util.QueryPage;

/**
 * @Author Zzy
 * @Date 2020/12/26
 */
public interface LoginLogService extends IService<SysLoginLog> {

    /*分页查询用户登录日志*/
    IPage<SysLoginLog> list(QueryPage queryPage,SysLoginLog sysLoginLog);

    /*根据id删除日志*/
    void delete(Long id);

    /*保存登录日志*/
    void saveLoginLog(SysLoginLog sysLoginLog);
}
