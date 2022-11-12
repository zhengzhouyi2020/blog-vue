package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**标签实体类
 * @Author Zzy
 * @Date 2020/11/17
 */
@Data
@TableName(value = "tbl_tag")
public class SysTag implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id; //主键

    private String name;  //标签名

    @TableField(exist = false)
    private Long count; //总数

    public SysTag() {
    }

    public SysTag(String name) {
        this.name = name;
    }
}
