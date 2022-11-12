package com.zzy.utils.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Zzy
 * @Date 2020/12/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPage implements Serializable {

    /*当前页码*/
    private int pageNo;
    /*总页数*/
    private int pageSize;
}
