package com.zzy.controller;

import com.zzy.entity.SysLog;
import com.zzy.service.LogService;
import com.zzy.utils.constant.CommonConstant;
import com.zzy.utils.controller.BaseController;
import com.zzy.utils.exception.GlobalException;
import com.zzy.utils.util.QueryPage;
import com.zzy.utils.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 操作日志controller层
 * @Author Zzy
 * @Date 2021/1/19
 */
@RestController
@RequestMapping(CommonConstant.BASE_API+"/log")
@Api(value = "LogController", tags = {"日志管理接口"})
public class LogController extends BaseController {
    @Autowired
    private LogService logService;

    @PostMapping("/list")
    public Result findByPage(@RequestBody SysLog sysLog, QueryPage queryPage){
        return new Result<>(super.getData(logService.list(sysLog,queryPage)));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        try{
            logService.delete(id);
            return new Result();
        }catch(Exception e){
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
