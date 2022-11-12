package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.entity.SysCategory;
import com.zzy.entity.SysTag;
import com.zzy.mapper.TagMapper;
import com.zzy.service.ArticleTagService;
import com.zzy.service.TagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper,SysTag> implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public List<SysTag> list(SysTag sysTag) {
        LambdaQueryWrapper<SysTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(sysTag.getName()),SysTag::getName, sysTag.getName());
        queryWrapper.orderByDesc(SysTag::getId);
        return tagMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<SysTag> list(SysTag sysTag, QueryPage queryPage) {
        IPage<SysTag> page=new Page<>(queryPage.getPageNo() ,queryPage.getPageSize());
        LambdaQueryWrapper<SysTag> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(sysTag.getName()),SysTag::getName, sysTag.getName());
        queryWrapper.orderByDesc(SysTag::getId);
        return tagMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<SysTag> findByArticleId(Long id) {
        return tagMapper.findTagByArticleId(id);
    }

    @Override
    public void add(SysTag sysTag) {
        if(!exist(sysTag)){
            tagMapper.insert(sysTag);
        }
    }

    private boolean exist(SysTag sysTag) {
        LambdaQueryWrapper<SysTag> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysTag::getName,sysTag.getName());
        return tagMapper.selectList(queryWrapper).size()>0;
    }

    @Override
    @Transactional
    public void update(SysTag sysTag) {
        tagMapper.updateById(sysTag);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        tagMapper.deleteById(id);
        articleTagService.deleteByTagId(id);

    }
}
