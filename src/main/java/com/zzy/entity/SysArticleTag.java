package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章实体类
 * @Author Zzy
 * @Date 2020/11/17
 */
@Data
@TableName("tbl_article_tag")
public class SysArticleTag implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id; //主键

    @TableField("article_id")
    private Long articleId; //文章id


    @TableField("tag_id")
    private Long tagId; //标签id

    public SysArticleTag(Long articleId, Long tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
