package com.zzy.utils.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzy.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;

/**controller公共方法提取
 * @Author Zzy
 * @Date 2020/12/27
 */
public class BaseController {
    protected Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    protected SysUser getCurrentUser(){
        return (SysUser) getSubject().getPrincipal();
    }

    protected Session getSession(){
        return getSubject().getSession();
    }

    protected Session getSession(Boolean flag){
        return getSubject().getSession(flag);
    }

    protected void login(AuthenticationToken token){
        getSubject().login(token);
    }

    public Map<String, Object> getData(IPage<?> page) {
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return data;
    }

    public Map<String, Object> getToken() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", getSession().getId());
        return map;
    }
}
