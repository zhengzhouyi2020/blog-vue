package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**分类实体类
 * @Author Zzy
 * @Date 2020/11/17
 */
@Data
@TableName("tbl_category")
public class SysCategory implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id; //主键

    private String name; //分类名
}
