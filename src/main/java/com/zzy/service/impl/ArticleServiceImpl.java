package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.entity.*;
import com.zzy.mapper.ArticleMapper;
import com.zzy.service.*;
import com.zzy.utils.util.QueryPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/27
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,SysArticle> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Override
    public List<SysArticle> findByTagId(Long id) {
        return articleMapper.findByTag(id);
    }

    @Override
    public List<SysArticle> findByCategoryId(Long id) {
        return articleMapper.findByCategory(id);
    }

    @Override
    public IPage<SysArticle> list(SysArticle sysArticle, QueryPage queryPage) {
        LambdaQueryWrapper<SysArticle> queryWrapper=new LambdaQueryWrapper<>();
        IPage<SysArticle> page=new Page<>(queryPage.getPageNo(),queryPage.getPageSize());
        queryWrapper.orderByDesc(SysArticle::getId);
        queryWrapper.like(StringUtils.isNotBlank(sysArticle.getTitle()), SysArticle::getTitle, sysArticle.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(sysArticle.getAuthor()), SysArticle::getAuthor, sysArticle.getAuthor());
        IPage<SysArticle> selectPage =articleMapper.selectPage(page, queryWrapper);
        findInit(selectPage.getRecords());
        return selectPage;

    }

    /*封装文章 分类 标签数据*/
    private void findInit(List<SysArticle> list){
        if(list!=null){
            for (SysArticle article:
                 list) {
                List<SysCategory> sysCategoryList=categoryService.findByArticleId(article.getId());
                if(sysCategoryList.size()>0){
                article.setCategory(sysCategoryList.get(0));
                }
                List<SysTag> sysTagList=tagService.findByArticleId(article.getId());
                article.setTags(sysTagList);
            }
        }
    }


    @Override
    @Transactional
    public SysArticle findById(Long id) {
        SysArticle sysArticle=articleMapper.selectById(id);
        if(sysArticle!=null){
            List<SysCategory> sysCategoryList=categoryService.findByArticleId(id);
            if(sysCategoryList.size()>0){
                sysArticle.setCategory(sysCategoryList.get(0));
            }
            List<SysTag> sysTagList=tagService.findByArticleId(id);
            sysArticle.setTags(sysTagList);
        }
        return sysArticle;


    }

    @Override
    @Transactional
    public void add(SysArticle sysArticle) {
        sysArticle.setCreateTime(new Date());
        articleMapper.insert(sysArticle);
        updateArticleCategoryTag(sysArticle);
    }

    private void updateArticleCategoryTag(SysArticle sysArticle) {
        if (sysArticle.getId() != 0) {
            if (sysArticle.getCategory() != null) {
                articleCategoryService.deleteByArticleId(sysArticle.getId());
                SysCategory sysCategory = categoryService.getById(sysArticle.getCategory());
                if (sysCategory != null) {
                    articleCategoryService.add(new SysArticleCategory(sysArticle.getId(), sysCategory.getId()));
                }
            }
            if (sysArticle.getTags() != null && sysArticle.getTags().size() > 0) {
                articleTagService.deleteByArticleId(sysArticle.getId());
                sysArticle.getTags().forEach(tag -> {
                    articleTagService.add(new SysArticleTag(sysArticle.getId(), tag.getId()));
                });
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id!=null&&id!=0){
            articleCategoryService.deleteByArticleId(id);
            articleTagService.deleteByArticleId(id);
            articleMapper.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void update(SysArticle sysArticle) {
        articleMapper.updateById(sysArticle);
        updateArticleCategoryTag(sysArticle);
    }
}
