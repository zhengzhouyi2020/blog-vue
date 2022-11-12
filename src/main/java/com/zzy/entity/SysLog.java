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

/**日志实体类
 * @Author Zzy
 * @Date 2020/11/17
 */
@Data
@ToString
@TableName(value = "tbl_log")
public class SysLog implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;  //主键

    private String username; //用户名

    private String operation;

    private Long time;  //操作时长

    private String method;  //操作方法

    private String params; //操作参数

    private String ip;  //操作ip地址

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;  //操作时间

    private String location;
}
