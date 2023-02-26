package com.zzy.controller;


import com.zzy.entity.SysLoginLog;
import com.zzy.service.LoginLogService;
import com.zzy.utils.constant.CommonConstant;
import com.zzy.utils.controller.BaseController;
import com.zzy.utils.exception.GlobalException;
import com.zzy.utils.util.QueryPage;
import com.zzy.utils.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 登录日志controller层
 * @Author Zzy
 * @Date 2021/1/19
 */
@RestController
@RequestMapping(CommonConstant.BASE_API+"/loginlog")
@Api(value = "LoginLogController", tags = {"登录日志管理接口"})
public class LoginLogController extends BaseController {
    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/list")
    public Result findByPage(@RequestBody SysLoginLog sysLoginLog, QueryPage queryPage){
        return new Result<>(super.getData(loginLogService.list(queryPage,sysLoginLog)));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        try{
            loginLogService.delete(id);
            return new Result();
        }catch(Exception e){
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
