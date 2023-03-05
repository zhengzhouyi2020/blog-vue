package com.zzy.utils.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author Zzy
 * @Date 2020/12/25
 */
@Data
@SpringBootConfiguration
@PropertySource("classpath:zzy.properties")
@ConfigurationProperties(prefix = "zzy")
@Component
public class ZzyProperties {

    private ShiroProperties shiro=new ShiroProperties();

    private SwaggerProperties swagger=new SwaggerProperties();

    private QiniuProperties qiniu=new QiniuProperties();
}
