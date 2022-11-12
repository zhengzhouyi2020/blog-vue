package com.zzy.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.entity.SysUser;
import com.zzy.mapper.UserMapper;
import com.zzy.service.UserService;
import com.zzy.utils.util.MD5Util;
import com.zzy.utils.util.SplineChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,SysUser> implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MD5Util md5Util;

    @Override
    public SysUser findByName(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername,username);
        List<SysUser> list=userMapper.selectList(queryWrapper);
        return list.size()>0?list.get(0):null;
    }

    @Override
    public SysUser checkName(String name, String currentName) {
        LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername,name);
        queryWrapper.ne(SysUser::getUsername,currentName);
        List<SysUser> list=userMapper.selectList(queryWrapper);
        return list.size()>0?list.get(0):null;
    }

    @Override
    @Transactional
    public void add(SysUser sysUser) {
        String encryptPassword=md5Util.encryptPassword(sysUser.getPassword());
        sysUser.setPassword(encryptPassword);
        sysUser.setAvatar("/img/avatar/default.jpg");
        sysUser.setCreateTime(new Date());
        userMapper.insert(sysUser);

    }

    @Override
    @Transactional
    public void updatePassword(SysUser sysUser) {
        SysUser user=new SysUser();
        user.setId(sysUser.getId());
        user.setPassword(md5Util.encryptPassword(sysUser.getPassword()));
        userMapper.updateById(user);
    }

    @Override
    @Transactional
    public void update(SysUser sysUser) {
        sysUser.setPassword(null);
        userMapper.updateById(sysUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public List<Long[]> chart() {
        List<Long[]> splineChart=new ArrayList<>();
        List<SplineChart> splineChartList=userMapper.chart();
        if(splineChartList.size()>0){
            splineChartList.forEach(item->{
                Long[] d={DateUtil.parse(item.getTime(),"yyyy-MM-dd").getTime(), item.getNum()};
                splineChart.add(d);

            });
        }
        return splineChart;
    }
}
