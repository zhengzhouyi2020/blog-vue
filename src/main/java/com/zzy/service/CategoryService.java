package com.zzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.entity.SysCategory;
import com.zzy.utils.util.QueryPage;

import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/26
 */
public interface CategoryService extends IService<SysCategory> {
    /*分页查询分类*/
    IPage<SysCategory> list(SysCategory sysCategory, QueryPage queryPage);

    /*查询分类，关键字查询*/
    List<SysCategory> list(SysCategory sysCategory);

    /*根据文章id查询分类*/
    List<SysCategory> findByArticleId(Long id);

    /*新增分类*/
    void add(SysCategory sysCategory);

    /*删除分类*/
    void delete(Long id);

    /*更新分类*/
    void update(SysCategory sysCategory);


}
