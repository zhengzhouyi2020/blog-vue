package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.entity.SysLoginLog;
import com.zzy.mapper.LoginLogMapper;
import com.zzy.service.LoginLogService;
import com.zzy.utils.util.QueryPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Zzy
 * @Date 2020/12/26
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper,SysLoginLog> implements LoginLogService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public IPage<SysLoginLog> list(QueryPage queryPage, SysLoginLog sysLoginLog) {
        IPage<SysLoginLog> page=new Page<>(queryPage.getPageNo(),queryPage.getPageSize());
        LambdaQueryWrapper<SysLoginLog> queryWrapper=new LambdaQueryWrapper<SysLoginLog>();
        queryWrapper.like(StringUtils.isNotBlank(sysLoginLog.getLocation()),SysLoginLog::getLocation,sysLoginLog.getLocation());
        queryWrapper.orderByDesc(SysLoginLog::getCreateTime);
        return loginLogMapper.selectPage(page,queryWrapper);

    }

    @Override
    public void delete(Long id) {
        loginLogMapper.deleteById(id);
    }

    @Override
    public void saveLoginLog(SysLoginLog sysLoginLog) {
        loginLogMapper.insert(sysLoginLog);
    }
}
