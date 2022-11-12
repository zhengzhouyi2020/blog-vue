package com.zzy.utils.properties;

import lombok.Data;

/**
 * @Author Zzy
 * @Date 2020/12/25
 */
@Data
public class ShiroProperties {
    private long sessionTimeout;
    private int cookieTimeout;
    private String anonUrl;
    private String loginUrl;
    private String successUrl;
    private String logoutUrl;
    private String cipherKey;
}
