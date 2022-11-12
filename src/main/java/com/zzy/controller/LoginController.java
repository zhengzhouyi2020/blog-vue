package com.zzy.controller;


import com.zzy.entity.SysLoginLog;
import com.zzy.entity.SysUser;
import com.zzy.service.LoginLogService;
import com.zzy.service.UserService;
import com.zzy.utils.constant.CommonConstant;
import com.zzy.utils.controller.BaseController;
import com.zzy.utils.exception.GlobalException;
import com.zzy.utils.util.*;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description 登录的controller层
 * @Author Zzy
 * @Date 2021/1/19
 */
@RestController
@RequestMapping(CommonConstant.BASE_API)
public class  LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MD5Util md5Util;

    @Autowired
    private LoginLogService loginLogService;


    /**
     * 登录接口
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/login")
    public Result login(HttpServletRequest httpServletRequest){
        String username=httpServletRequest.getParameter("username");
        String password=httpServletRequest.getParameter("password");
        System.out.println(username);
        Subject subject=getSubject();
        String encrypt_password=md5Util.encryptPassword(password);
        UsernamePasswordToken token=new UsernamePasswordToken();
        try{
            subject.login(token);
            HttpServletRequest request= HttpContextUtil.getHttpServletRequest();
            //记录登录日志
            SysLoginLog sysLoginLog=new SysLoginLog();
            //获取http请求
            String ip= IPUtil.getIpAddr(request);
            sysLoginLog.setIp(ip);
            sysLoginLog.setUsername(super.getCurrentUser().getUsername());
            sysLoginLog.setLocation(AddressUtil.getAddress(ip));
            sysLoginLog.setCreateTime(new Date());
            String header=request.getHeader(CommonConstant.USER_AGENT);
            UserAgent userAgent= UserAgent.parseUserAgentString(header);
            Browser browser=userAgent.getBrowser();
            OperatingSystem operatingSystem=userAgent.getOperatingSystem();
            sysLoginLog.setDevice(browser.getName() + " -- " + operatingSystem.getName());
            loginLogService.saveLoginLog(sysLoginLog);
            return new Result<>(this.getToken());

        }catch(Exception e){
            e.printStackTrace();
            return new Result<>(e);
        }
    }

    /**
     * 注册接口
     * @param sysUser
     * @return
     */
    @PostMapping("/register")
    public Result save(@RequestBody SysUser sysUser){
        try{
            userService.add(sysUser);
            return new Result();
        }catch(Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 注销接口
     * @return
     */
    @GetMapping("/logout")
    public Result logout(){
        Subject subject= getSubject();
        subject.logout();
        return new Result<>();
    }
}
