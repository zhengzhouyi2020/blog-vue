package com.zzy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.entity.SysLink;
import com.zzy.utils.util.QueryPage;

import java.util.List;

/**
 * @Author Zzy
 * @Date 2020/12/26
 */
public interface LinkService extends IService<SysLink> {


    List<SysLink> list(SysLink sysLink);

    IPage<SysLink> list(SysLink sysLink, QueryPage queryPage);

    /*新增连接*/
    void add(SysLink sysLink);

    /*删除链接*/
    void delete(Long id);

    /*更新链接*/
    void update(SysLink sysLink);
}
