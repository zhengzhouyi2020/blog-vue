package com.zzy.controller;

import com.zzy.entity.SysArticle;
import com.zzy.entity.SysCategory;
import com.zzy.service.CategoryService;
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
 * @Description 分类controller层
 * @Author Zzy
 * @Date 2021/1/19
 */
@RestController
@RequestMapping(CommonConstant.BASE_API+"/category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/list")
    public Result<Map<String,Object>> list(@RequestBody SysCategory sysCategory, QueryPage queryPage){
        return new Result<>(super.getData(categoryService.list(sysCategory,queryPage)));
    }

    @PostMapping("/filter/list")
    public Result list(@RequestBody SysCategory sysCategory){
        return new Result(categoryService.list(sysCategory));
    }

    @GetMapping("/{id}")
    public Result<SysCategory>findById(@PathVariable("id") Long id){
        return new Result<>(categoryService.getById(id));
    }

    @PostMapping
    @Log("新增分类")
    public Result add(@RequestBody SysCategory sysCategory){
        try{
            categoryService.add(sysCategory);
            return new Result();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping
    @Log("更新分类")
    public Result update(@RequestBody SysCategory sysCategory){
        try{
            categoryService.update(sysCategory);
            return  new Result();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new   GlobalException(e.getMessage());
        }
    }




    @DeleteMapping("/{id}")
    @Log("删除分类")
    public Result delete(@PathVariable Long id){
        try{
            categoryService.delete(id);
            return new Result();
        }catch(Exception e){
            throw new GlobalException(e.getMessage());
        }
    }



}
