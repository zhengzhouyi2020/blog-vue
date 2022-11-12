package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**用户实体类
 * @Author Zzy
 * @Date 2020/11/17
 */
@Data
//@Accessors(chain = true)
@TableName(value = "tbl_user")
public class SysUser implements Serializable {


    @TableId(type = IdType.AUTO)
    private Long id;  //主键

    private String username; //用户名
    private String password; //密码
    private String avatar; //头像
    private String email; //邮箱
    private String description; //简介

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime; //创建时间
}
