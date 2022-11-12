package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.entity.SysCategory;
import com.zzy.mapper.CategoryMapper;
import com.zzy.service.ArticleCategoryService;
import com.zzy.service.CategoryService;
import com.zzy.utils.util.QueryPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/27
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,SysCategory> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleCategoryService articleCategoryService;
    @Override
    public IPage<SysCategory> list(SysCategory sysCategory, QueryPage queryPage) {
        IPage<SysCategory> page = new Page<>(queryPage.getPageNo(), queryPage.getPageSize());
        LambdaQueryWrapper<SysCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(sysCategory.getName()), SysCategory::getName, sysCategory.getName());
        queryWrapper.orderByDesc(SysCategory::getId);
        return categoryMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<SysCategory> list(SysCategory sysCategory) {
        LambdaQueryWrapper<SysCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(sysCategory.getName()), SysCategory::getName, sysCategory.getName());
        queryWrapper.orderByDesc(SysCategory::getId);
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public List<SysCategory> findByArticleId(Long id) {
        return categoryMapper.findCategoryByArticleId(id);
    }

    @Override
    @Transactional
    public void add(SysCategory sysCategory) {
        if(!exist(sysCategory)){
            categoryMapper.insert(sysCategory);
        }
    }

    private boolean exist(SysCategory sysCategory) {
        LambdaQueryWrapper<SysCategory> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysCategory::getName,sysCategory.getName());
        return categoryMapper.selectList(queryWrapper).size()>0;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoryMapper.deleteById(id);
        articleCategoryService.deleteByCategoryId(id);
    }

    @Override
    @Transactional
    public void update(SysCategory sysCategory) {
        categoryMapper.updateById(sysCategory);
    }
}
