package com.zzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.entity.SysArticle;
import com.zzy.utils.util.QueryPage;

import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/26
 */
public interface ArticleService extends IService<SysArticle> {

    /*根据标签id删除*/
    List<SysArticle> findByTagId(Long id);

    /*根据分类id删除*/
    List<SysArticle> findByCategoryId(Long id);

    /*分页查询*/
    IPage<SysArticle> list(SysArticle sysArticle, QueryPage queryPage);

    /*根据文章id查询*/
    SysArticle findById(Long id);

    /*新增文章*/
    void add(SysArticle sysArticle);

    /*删除文章*/
    void delete(Long id);

    /*更新文章*/
    void update(SysArticle sysArticle);
}
