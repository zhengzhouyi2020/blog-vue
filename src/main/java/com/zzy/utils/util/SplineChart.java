package com.zzy.utils.util;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Zzy
 * @Date 2020/12/25
 */
@Data
public class SplineChart implements Serializable {
    private String time;

    private Long num;
}
