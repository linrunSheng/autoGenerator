//package com.lhy.example.sys.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
//import com.lhy.common.web.SimpleCrudRequestMappingHandlerMapping;
//import com.lhy.common.web.converter.StringConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import javax.annotation.PostConstruct;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.stream.IntStream;
//
///**
// * TODO
// * 请求日期字符串转{@link java.time.LocalDateTime } 需要全局配置<br/>
// * 返回json串{@link java.time.LocalDateTime }转 字符串 } 需要全局配置
// */
//@Configuration
//public class WebMvcConfiguration extends WebMvcConfigurationSupport {
//
//    @Autowired
//    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
//
//    /**
//     * {@inheritDoc}
//     * </br>注册controller映射
//     * 如果有相同注解controller 以注解优先
//     */
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.setOrder(0);
//        registry.addViewController("/sp-userview").setViewName("example/sp-user");
//    }
//
//    @Override
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        return new SimpleCrudRequestMappingHandlerMapping();
//    }
//
//    @Override
//    protected void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new StringConverter.StringToLocalDateTimeConverter());
//        registry.addConverter(new StringConverter.StringToLocalDateConverter());
//    }
//
//    @Autowired
//    ApplicationContext applicationContext;
//    @PostConstruct
//    public void method(){
//        FormatterRegistry bean = applicationContext.getBean(FormatterRegistry.class);
//    }
//
//    /**
//     * springboot2 必须添加
//     *
//     * @param registry
//     */
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//    /**
//     * 不能用内置的objectMapper 没法修改Serializer序列化规则，因为内置的objectMapper 的javaTimeModule已经注册过一次默认"yyyy-MM-ddTHH:mm:ss格式的
//     * 再次注册无用
//     *
//     * @return
//     */
//    @Bean
//    public ObjectMapper objectMapper() {
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
//        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().modules(javaTimeModule).build();
////        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return objectMapper;
//    }
//
//
//    /**
//     * WebMvcConfigurationSupport 会重行初始化MessageConverters bean 里存储的essageConverters 和
//     * spring mvc AbstractMessageConverterMethodProcessor 持有的Converter 不是同一个对象，
//     * 不管怎么设置 objectMapper 对mvc都不生效，或许是springboot的一个bug
//     * {@see HttpMessageConverters 193}
//     *
//     * @param converters
//     */
//    @Override
//    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        int index = IntStream.range(0, converters.size()).filter(i -> MappingJackson2HttpMessageConverter.class.isAssignableFrom(converters.get(i).getClass())).findFirst().orElse(0);
//        converters.remove(index);
//        converters.add(mappingJackson2HttpMessageConverter);
//    }
//
//
//}
