package com.lhy.tool.generator.annotation;

import java.lang.annotation.*;

/**
* @ClassName: ModelIgnoreAttribute
* @Description: 标记了该注解的成员变量不写入视图
* @author  luanhy
* @date 2018年1月3日 下午10:07:31
* @Copyright: Copyright (c) 2017 wisedu
*/
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ModelIgnoreAttribute {
}
