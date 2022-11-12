package com.zzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.entity.SysTag;

import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/25
 */
public interface TagMapper extends BaseMapper<SysTag> {
    List<SysTag> findTagByArticleId(long id);
}
