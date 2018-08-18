package com.lhy.example.sys.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 不用加入  {@code @EnableTransactionManagement}注解来启用事务,<br/>
 * 只要 {@code PlatformTransactionManager}  存在则开启
 */
@Configuration
public class MybatisPlusConfiguration {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
