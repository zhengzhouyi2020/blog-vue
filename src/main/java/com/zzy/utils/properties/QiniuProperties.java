package com.zzy.utils.properties;

import lombok.Data;

/**
 * @Author Zzy
 * @Date 2020/12/25
 */
@Data
public class QiniuProperties {
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String url;
}
