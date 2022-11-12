package com.zzy.controller;

import com.zzy.entity.SysTag;
import com.zzy.service.TagService;
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
 * @Description 标签controller层
 * @Author Zzy
 * @Date 2021/1/19
 */
@RestController
@RequestMapping(CommonConstant.BASE_API+"/tag")
public class TagController extends BaseController {
    @Autowired
    private TagService tagService;

    @PostMapping("/list")
    public Result<Map<String,Object>> list(@RequestBody SysTag sysTag, QueryPage queryPage){
        return new Result<>(super.getData(tagService.list(sysTag,queryPage)));
    }

    @PostMapping("/filter/list")
    public Result list(@RequestBody SysTag sysTag){
        return new Result(tagService.list(sysTag));
    }

    @GetMapping("/{id}")
    public Result<SysTag>findById(@PathVariable("id") Long id){
        return new Result<>(tagService.getById(id));
    }

    @PostMapping
    @Log("新增标签")
    public Result add(@RequestBody SysTag sysTag){
        try{
            tagService.add(sysTag);
            return new Result();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping
    @Log("更新标签")
    public Result update(@RequestBody SysTag sysTag){
        try{
            tagService.update(sysTag);
            return  new Result();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new   GlobalException(e.getMessage());
        }
    }




    @DeleteMapping("/{id}")
    @Log("删除标签")
    public Result delete(@PathVariable Long id){
        try{
            tagService.delete(id);
            return new Result();
        }catch(Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

}
