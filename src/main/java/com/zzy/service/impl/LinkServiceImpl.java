package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.entity.SysLink;
import com.zzy.entity.SysTag;
import com.zzy.mapper.LinkMapper;
import com.zzy.service.LinkService;
import com.zzy.utils.util.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/27
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper,SysLink> implements LinkService {
    @Autowired
    private LinkMapper linkMapper;

    @Override
    public List<SysLink> list(SysLink sysLink) {
        LambdaQueryWrapper<SysLink> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(org.apache.commons.lang3.StringUtils.isNotBlank(sysLink.getName()), SysLink::getName, sysLink.getName());
        queryWrapper.orderByDesc(SysLink::getId);
        return linkMapper.selectList(queryWrapper);

    }

    @Override
    public IPage<SysLink> list(SysLink sysLink, QueryPage queryPage) {
        IPage<SysLink> page=new Page<>(queryPage.getPageNo(),queryPage.getPageSize());
        LambdaQueryWrapper<SysLink> queryWrapper=new LambdaQueryWrapper<SysLink>();
        queryWrapper.like(StringUtils.isNotBlank(sysLink.getName()),SysLink::getName,sysLink.getName());
        return linkMapper.selectPage(page,queryWrapper);
    }

    @Override
    @Transactional
    public void add(SysLink sysLink) {
        linkMapper.insert(sysLink);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        linkMapper.deleteById(id);

    }

    @Override
    @Transactional
    public void update(SysLink sysLink) {
        linkMapper.updateById(sysLink);
    }
}
