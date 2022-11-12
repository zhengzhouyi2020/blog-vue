package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章实体类
 * @Author Zzy
 * @Date 2020/11/17
 */
@Data
@TableName("tbl_article")
public class SysArticle implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //主键

    private String title; //题目
    private String author; //作者
    private String description;  //描述
    private String content;  //内容

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime; //创建时间

    @TableField(exist = false)
    private List<SysTag> tags; //标签

    @TableField(exist = false)
    private SysCategory category; //分类
}
