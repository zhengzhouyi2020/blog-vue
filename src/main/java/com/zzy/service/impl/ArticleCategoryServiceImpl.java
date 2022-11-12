package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.entity.SysArticleCategory;
import com.zzy.mapper.ArticleCategoryMapper;
import com.zzy.service.ArticleCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Zzy
 * @Date 2020/12/27
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper,SysArticleCategory> implements ArticleCategoryService {
    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;


    @Override
    public void add(SysArticleCategory sysArticleCategory) {
        if(!exist(sysArticleCategory)){
            articleCategoryMapper.insert(sysArticleCategory);
        }
    }

    private boolean exist(SysArticleCategory sysArticleCategory) {
        LambdaQueryWrapper<SysArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArticleCategory::getArticleId, sysArticleCategory.getArticleId());
        queryWrapper.eq(SysArticleCategory::getCategoryId,sysArticleCategory.getCategoryId());
        return articleCategoryMapper.selectList(queryWrapper).size()>0;
    }

    @Override
    @Transactional
    public void deleteByArticleId(Long id) {
        LambdaQueryWrapper<SysArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArticleCategory::getArticleId, id);
        articleCategoryMapper.delete(queryWrapper);
    }

    @Override
    @Transactional
    public void deleteByCategoryId(Long id) {
        LambdaQueryWrapper<SysArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArticleCategory::getCategoryId, id);
        articleCategoryMapper.delete(queryWrapper);
    }
}
