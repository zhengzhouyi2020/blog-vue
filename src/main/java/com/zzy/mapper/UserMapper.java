package com.zzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.entity.SysUser;
import com.zzy.utils.util.SplineChart;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/25
 */
public interface UserMapper extends BaseMapper<SysUser> {

    @Select("select date_format(create_time,'%Y-%m-%d') time, count(*) num from tbluser group by date_format(create_time, '%Y-%m-%d')  ")
    List<SplineChart> chart();
}
