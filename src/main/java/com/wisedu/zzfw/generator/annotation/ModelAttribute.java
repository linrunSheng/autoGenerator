package com.wisedu.zzfw.generator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

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
