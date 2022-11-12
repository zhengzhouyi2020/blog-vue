package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**链接实体类
 * @Author Zzy
 * @Date 2020/11/17
 */
@Data
@TableName("tbl_link")
public class SysLink implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;  //主键

    private String name; //链接名称

    private String url; //地址
}
