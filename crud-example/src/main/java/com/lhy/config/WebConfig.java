package com.lhy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * {@inheritDoc}
     * </br>注册controller映射
     * 如果有相同注解controller 以注解优先
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(0);
        registry.addViewController("/userview").setViewName("example/user");
    }
}
