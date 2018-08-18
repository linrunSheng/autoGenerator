package com.lhy.example.sys.config;

import com.lhy.common.web.SimpleCrudRequestMappingHandlerMapping;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * TODO
 * 请求日期字符串转{@link java.time.LocalDateTime } 需要全局配置<br/>
 * 返回json串{@link java.time.LocalDateTime }转 字符串 } 需要全局配置
 */
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


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
