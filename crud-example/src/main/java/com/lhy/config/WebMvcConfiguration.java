package com.lhy.config;

import com.lhy.common.web.SimpleCrudRequestMappingHandlerMapping;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    /**
     * {@inheritDoc}
     * </br>注册controller映射
     * 如果有相同注解controller 以注解优先
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(0);
        registry.addViewController("/sp-userview").setViewName("example/sp-user");
    }

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new SimpleCrudRequestMappingHandlerMapping();
    }


}
