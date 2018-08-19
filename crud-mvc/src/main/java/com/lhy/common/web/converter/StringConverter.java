package com.lhy.common.web.converter;

import com.lhy.common.web.util.LocalDateTimeUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 转换解析器
 *
 * @author wangqingguo 2017/9/25
 */
@Configuration
public class StringConverter {

//    @Bean("stringToLocalDateTimeConverter")
//    public Converter<String, LocalDateTime> stringToLocalDateTimeConverter() {
//        //不能使用lambda表达式 ResolvableType无法解析lambda表达式泛型参数
//        return new Converter<String, LocalDateTime>() {
//
//            @Override
//            public LocalDateTime convert(String s) {
//                return LocalDateTimeUtil.parseTime(s);
//            }
//        };
//    }
//
//    @Bean("stringToLocalDateConverter")
//    public Converter<String, LocalDate> stringToLocalDateConverter() {
//        //不能使用lambda表达式 ResolvableType无法解析lambda表达式泛型参数
//        return new Converter<String, LocalDate>() {
//            @Override
//            public LocalDate convert(String source) {
//                return LocalDateTimeUtil.parseDate(source);
//            }
//        };
//    }


    /**
     * 时间参数接收转换器，将字符串转为时间类型
     */
    public static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

        @Override
        public LocalDateTime convert(String s) {
            return LocalDateTimeUtil.parseTime(s);
        }
    }

    /**
     * 日期参数接收转换器，将字符串转为日期类型
     */
    public static class StringToLocalDateConverter implements Converter<String, LocalDate> {

        @Override
        public LocalDate convert(String s) {
            return LocalDateTimeUtil.parseDate(s);
        }
    }
}
