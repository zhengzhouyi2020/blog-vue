package com.zzy.controller;

import com.zzy.entity.SysLink;
import com.zzy.service.LinkService;
import com.zzy.utils.annotation.Log;
import com.zzy.utils.constant.CommonConstant;
import com.zzy.utils.controller.BaseController;
import com.zzy.utils.exception.GlobalException;
import com.zzy.utils.util.QueryPage;
import com.zzy.utils.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description 友情链接controller层
 * @Author Zzy
 * @Date 2021/1/19
 */
@RestController
@RequestMapping(CommonConstant.BASE_API+"/link")
public class LinkController extends BaseController {
    @Autowired
    private LinkService linkService;

    @PostMapping("/list")
    public Result list(@RequestBody SysLink sysLink, QueryPage queryPage){
        return new Result<>(super.getData(linkService.list(sysLink,queryPage)));
    }

    @PostMapping("/filter/list")
    public Result list(@RequestBody SysLink sysLink){
        return new Result(linkService.list(sysLink));
    }

    @GetMapping("/{id}")
    public Result<SysLink>findById(@PathVariable("id") Long id){
        return new Result<>(linkService.getById(id));
    }

    @PostMapping
    @Log("新增友链")
    public Result add(@RequestBody SysLink sysLink){
        try{
            linkService.add(sysLink);
            return new Result();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping
    @Log("更新友链")
    public Result update(@RequestBody SysLink sysLink){
        try{
            linkService.update(sysLink);
            return  new Result();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new   GlobalException(e.getMessage());
        }
    }




    @DeleteMapping("/{id}")
    @Log("删除友链")
    public Result delete(@PathVariable Long id){
        try{
            linkService.delete(id);
            return new Result();
        }catch(Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

}
