package com.zzy.utils.util;

import com.zzy.utils.constant.CommonConstant;
import com.zzy.utils.properties.ShiroProperties;
import com.zzy.utils.properties.ZzyProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Zzy
 * @Date 2020/12/25
 */
@Component
public class MD5Util {

    @Autowired
    private ZzyProperties zzyProperties;

    public String encryptPassword(String password){
        ShiroProperties shiro = zzyProperties.getShiro();
        if (password == null) {
            return null;
        }
        return new SimpleHash(CommonConstant.ALGORITHM_NAME,
                StringUtils.lowerCase(StringUtils.lowerCase(password)),
                shiro.getCipherKey(),
                CommonConstant.HASH_ITERATIONS).toHex();
    }
}
