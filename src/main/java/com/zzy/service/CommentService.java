package com.zzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.entity.SysComment;
import com.zzy.utils.util.QueryPage;

import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/26
 */
public interface CommentService extends IService<SysComment> {
    /*根据文章id查询所有的评论*/
    List<SysComment> findByArticleId(Long id);

    /*分页查询*/
    IPage<SysComment> list(SysComment sysComment, QueryPage queryPage);

    /*增加评论*/
    void add(SysComment sysComment);

    /*删除评论*/
    void delete(Long id);

    /*图标数据*/
    List<Long[]> chart();
}
