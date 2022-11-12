package com.zzy.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zzy.entity.SysArticle;
import com.zzy.entity.SysUser;
import com.zzy.service.ArticleService;
import com.zzy.utils.annotation.Log;
import com.zzy.utils.constant.CommonConstant;
import com.zzy.utils.controller.BaseController;
import com.zzy.utils.exception.GlobalException;
import com.zzy.utils.util.QueryPage;
import com.zzy.utils.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 文章controller层
 * @Author Zzy
 * @Date 2021/1/17
 */
@RestController
@RequestMapping(CommonConstant.BASE_API+"/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/findByCategory/{id}")
    public Result findByCategory(@PathVariable("id") Long id){
        return new Result<>(articleService.findByCategoryId(id));
    }

    @GetMapping("/findByTag/{id}")
    public Result findByTag(@PathVariable("id") Long id){
        return new Result<>(articleService.findByTagId(id));
    }

    @PostMapping("/list")
    public Result list(@RequestBody SysArticle sysArticle, QueryPage queryPage) {
        return new Result<>(super.getData(articleService.list(sysArticle, queryPage)));
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Long id){
        return new Result<>(articleService.findById(id));
    }

    @PostMapping
    @Log("新增文章")
    public Result add(@RequestBody SysArticle sysArticle){
        try{
            sysArticle.setAuthor(this.getCurrentUser().getUsername());
            articleService.add(sysArticle);
            return new Result();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping
    @Log("更新文章")
    public Result update(@RequestBody SysArticle sysArticle){
        try{
            articleService.update(sysArticle);
            return  new Result();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new   GlobalException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Log("删除文章")
    public Result delete(@PathVariable("id") Long id){
        try{
            articleService.delete(id);
            return new Result();
        }catch(Exception e){
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
