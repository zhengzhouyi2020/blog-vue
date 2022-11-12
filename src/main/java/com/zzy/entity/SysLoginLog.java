package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**登录日志实体类
 * @Author Zzy
 * @Date 2020/11/17
 */
@TableName("tbl_login_log")
@ToString
@Data
public class SysLoginLog implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id; //主键

    private String username; //用户名

    private String ip; //ip地址

    private String location; //所在地

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Date createTime;  //创建时间

    private String device;  //设备

    @TableField(exist = false)
    private String filedTime;
}
