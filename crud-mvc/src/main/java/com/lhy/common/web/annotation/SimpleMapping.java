package com.lhy.common.web.annotation;

import java.lang.annotation.*;

/**
 * CommonMapping
 * <p/>
 * 如果继承了BaseControllerImpl， 没有标记该注解，或者标记了该注解，并且继承了SimpleCrudControllerSupport的方法 才会注册mapping映射
 * @author hyluan
 * @date 2018/8/17 15:53
 * Copyright (c) 2018 wisedu
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SimpleMapping {

}
