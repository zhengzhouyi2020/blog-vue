package com.zzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.entity.SysArticle;

import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/25
 */
public interface ArticleMapper extends BaseMapper<SysArticle> {

    List<SysArticle> findByCategory(long id);

    List<SysArticle> findByTag(long id);

}
