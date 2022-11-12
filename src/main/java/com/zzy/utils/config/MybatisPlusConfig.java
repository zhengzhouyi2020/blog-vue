package com.zzy.utils.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis Plus配置
 * @Author Zzy
 * @Date 2020/12/25
 */
@Configuration
public class MybatisPlusConfig {
    /*
    * Mybatis-plus 分页插件*/
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor(){
        return new PaginationInnerInterceptor();
    }

}
