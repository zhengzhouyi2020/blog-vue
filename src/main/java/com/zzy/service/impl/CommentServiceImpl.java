package com.zzy.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.entity.SysComment;
import com.zzy.mapper.CommentMapper;
import com.zzy.service.CommentService;
import com.zzy.utils.util.QueryPage;
import com.zzy.utils.util.SplineChart;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/27
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper,SysComment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<SysComment> findByArticleId(Long id) {
        LambdaQueryWrapper<SysComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysComment::getArticleId,id);
        queryWrapper.orderByDesc(SysComment::getArticleId);
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<SysComment> list(SysComment sysComment, QueryPage queryPage) {
        IPage<SysComment> page = new Page<>(queryPage.getPageNo(), queryPage.getPageSize());
        LambdaQueryWrapper<SysComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(sysComment.getContent()), SysComment::getContent, sysComment.getContent());
        queryWrapper.orderByDesc(SysComment::getId);
        return commentMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void add(SysComment sysComment) {
        commentMapper.insert(sysComment);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commentMapper.deleteById(id);
    }

    @Override
    public List<Long[]> chart() {
        List<Long[]> splineChartList=new ArrayList<>();
        List<SplineChart> splineChart=commentMapper.chart();
        if(splineChart.size()>0){
            for (SplineChart sp:
                 splineChart) {
                Long[] temp={DateUtil.parse(sp.getTime(), "yyyy-MM-dd").getTime(),sp.getNum()};
                splineChartList.add(temp);
            }
        }
        return splineChartList;
    }
}
