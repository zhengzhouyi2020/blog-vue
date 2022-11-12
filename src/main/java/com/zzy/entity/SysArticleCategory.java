package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类实体类
 * @Author Zzy
 * @Date 2020/11/17
 */
@Data
@TableName("tbl_article_category")
public class SysArticleCategory implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id; //主键

    @TableField("article_id")
    private Long articleId; //文章id

    @TableField("category_id")
    private Long categoryId; //分类id

    public SysArticleCategory(Long articleId, Long categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }
}
