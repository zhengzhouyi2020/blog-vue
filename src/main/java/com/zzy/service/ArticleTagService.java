package com.zzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.entity.SysArticleCategory;
import com.zzy.entity.SysArticleTag;

/**
 * @Author Zzy
 * @Date 2020/12/26
 */
public interface ArticleTagService extends IService<SysArticleTag> {

    /*新增文章-标签*/
    void add(SysArticleTag sysArticleTag);

    /*根据文章id删除*/
    void deleteByArticleId(Long id);

    /*根据标签id删除*/
    void deleteByTagId(Long id);
}
