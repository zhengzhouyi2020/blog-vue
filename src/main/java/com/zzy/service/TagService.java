package com.zzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.entity.SysTag;
import com.zzy.utils.util.QueryPage;

import java.util.List;

/**
 * 标签实体类
 * @Author Zzy
 * @Date 2020/12/26
 */
public interface   TagService extends IService<SysTag> {

    /*关键字查询标签*/
    List<SysTag> list(SysTag sysTag);

    /*分页查询*/
    IPage<SysTag> list(SysTag sysTag, QueryPage queryPage);

    /*根据文章id查询标签*/
    List<SysTag> findByArticleId(Long id);



    /*新增标签*/
    void add(SysTag sysTag);

    /*更新标签*/
    void update(SysTag sysTag);

    /*删除标签*/
    void delete(Long id);
}
