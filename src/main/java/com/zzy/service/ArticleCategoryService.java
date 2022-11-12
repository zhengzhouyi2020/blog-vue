package com.zzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.entity.SysArticle;
import com.zzy.entity.SysArticleCategory;

/**
 * @Author Zzy
 * @Date 2020/12/26
 */
public interface ArticleCategoryService extends IService<SysArticleCategory> {

    /*新增文章-分类*/
    void add(SysArticleCategory sysArticleCategory);

    /*根据文章id删除*/
    void deleteByArticleId(Long id);

    /*根据分类id删除*/
    void deleteByCategoryId(Long id);
}
