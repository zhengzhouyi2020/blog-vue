package com.zzy.controller;


import com.zzy.entity.SysUser;
import com.zzy.service.UserService;
import com.zzy.utils.annotation.Log;
import com.zzy.utils.constant.CommonConstant;
import com.zzy.utils.controller.BaseController;
import com.zzy.utils.exception.GlobalException;
import com.zzy.utils.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 用户controller层
 * @Author Zzy
 * @Date 2021/1/19
 */
@RestController
@RequestMapping(CommonConstant.BASE_API+"/user")
@Api(value = "UserController", tags = {"用户功能接口"})
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public Result getInfo() {
        return new Result<>(this.getCurrentUser());
    }

    @GetMapping("/findByName")
    public Result findByName(String username) {
        return new Result<>(userService.findByName(username));
    }

    @GetMapping("/checkName")
    public Result checkName(String username) {
        return new Result<>(userService.checkName(username, this.getCurrentUser().getUsername()));
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Long id) {
        return new Result<>(userService.getById(id));
    }

    @PostMapping
    @Log("新增用户")
    public Result add(@RequestBody SysUser sysUser) {
        try {
            userService.add(sysUser);
            return new Result();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping
    @Log("更新用户")
    public Result update(@RequestBody SysUser sysUser) {
        try {
            userService.update(sysUser);
            return new Result();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping("/changePass")
    @Log("修改密码")
    public Result updatePass(@RequestBody SysUser sysUser) {
        try {
            userService.updatePassword(sysUser);
            return new Result();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Log("删除用户")
    public Result delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            return new Result();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @GetMapping("/chart")
    public Result chart() {
        return new Result<>(userService.chart());
    }


}
