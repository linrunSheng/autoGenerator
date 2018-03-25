package com.lhy.tool.generator.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
* @ClassName: ModelAttribute
* @Description: 可以设置写入视图的键别名
* @author  luanhy
* @date 2018年1月3日 下午10:07:31
* @Copyright: Copyright (c) 2017 wisedu
*/
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ModelAttribute {
	
	@AliasFor("key")
	String value() default "";
	
	@AliasFor("value")
	String key() default "";

}
