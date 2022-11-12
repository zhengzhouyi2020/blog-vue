package com.zzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.entity.SysCategory;

import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/25
 */
public interface CategoryMapper extends BaseMapper<SysCategory> {
    List<SysCategory> findCategoryByArticleId(long id);
}
