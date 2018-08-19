package com.lhy.example.sys.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.lhy.common.web.SimpleCrudRequestMappingHandlerMapping;
import com.lhy.common.web.converter.StringConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping(List<HandlerInterceptor> interceptors) {
        RequestMappingHandlerMapping mapping = new SimpleCrudRequestMappingHandlerMapping();
        mapping.setOrder(0);
        if (interceptors != null) {
            mapping.setInterceptors(interceptors);
        }
        return new SimpleCrudRequestMappingHandlerMapping();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringConverter.StringToLocalDateTimeConverter());
        registry.addConverter(new StringConverter.StringToLocalDateConverter());
    }

    /**
     * 不能用内置的objectMapper 没法修改Serializer序列化规则，因为内置的objectMapper 的javaTimeModule已经注册过一次默认"yyyy-MM-ddTHH:mm:ss格式的
     * 再次注册无用
     *
     * @return
     */
    @Bean
    public ObjectMapper objectMapper(JavaTimeModule javaTimeModule) {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().modules(javaTimeModule).build();
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    @Bean
    public JavaTimeModule javaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        return javaTimeModule;
    }

}
