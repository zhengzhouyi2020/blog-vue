package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.entity.SysArticleCategory;
import com.zzy.entity.SysArticleTag;
import com.zzy.mapper.ArticleTagMapper;
import com.zzy.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Zzy
 * @Date 2020/12/27
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, SysArticleTag> implements ArticleTagService {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public void add(SysArticleTag sysArticleTag) {
        if(!exist(sysArticleTag)){
            articleTagMapper.insert(sysArticleTag);
        }
    }

    private boolean exist(SysArticleTag sysArticleTag) {
        LambdaQueryWrapper<SysArticleTag> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArticleTag::getArticleId,sysArticleTag.getArticleId());
        queryWrapper.eq(SysArticleTag::getTagId,sysArticleTag.getTagId());
        return articleTagMapper.selectList(queryWrapper).size()>0;
    }


    @Override
    @Transactional
    public void deleteByArticleId(Long id) {
        LambdaQueryWrapper<SysArticleTag> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArticleTag::getArticleId,id);
        articleTagMapper.delete(queryWrapper);
    }

    @Override
    @Transactional
    public void deleteByTagId(Long id) {
        LambdaQueryWrapper<SysArticleTag> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArticleTag::getTagId,id);
        articleTagMapper.delete(queryWrapper);
    }
}
