package com.zzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.entity.SysUser;

import java.util.List;

/**
 * 用户
 * @Author Zzy
 * @Date 2020/12/26
 */
public interface UserService extends IService<SysUser> {

    /*根据名字查询用户*/
    SysUser findByName(String username);

    /*检查名字*/
    SysUser checkName(String name,String currentName);

    /*新增用户*/
    void add(SysUser sysUser);

    /*更新密码*/
    void updatePassword(SysUser sysUser);

    /*更新用户*/
    void update(SysUser sysUser);

    /*删除用户*/
    void delete(Long id);


    List<Long[]> chart();


}
