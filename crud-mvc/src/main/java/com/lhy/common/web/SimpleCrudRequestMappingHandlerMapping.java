package com.lhy.common.web;

import com.lhy.common.web.annotation.SimpleMapping;
import com.lhy.common.web.controller.SimpleCrudControllerSupport;
import com.lhy.common.web.util.ReflectionUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Map;

public class SimpleCrudRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    /**
     * detectHandlerMethods
     * <p/>
     * 覆盖注册mapping方法  SimpleCrudControllerSupport， 只有标记了该注解的method才会注册mapping映射
     *
     * @param handler
     * @return void
     * @throws
     * @date 2018/8/17 15:56
     */
    @Override
    protected void detectHandlerMethods(final Object handler) {
        Class<?> handlerType = (handler instanceof String ?
                obtainApplicationContext().getType((String) handler) : handler.getClass());

        if (handlerType != null) {
            final Class<?> userType = ClassUtils.getUserClass(handlerType);
            Map<Method, RequestMappingInfo> methods = MethodIntrospector.selectMethods(userType,
                    (MethodIntrospector.MetadataLookup<RequestMappingInfo>) method -> {
                        try {
                            return getMappingForMethod(method, userType);
                        } catch (Throwable ex) {
                            throw new IllegalStateException("Invalid mapping on handler class [" +
                                    userType.getName() + "]: " + method, ex);
                        }
                    });
            methods.entrySet().stream().filter(entity -> isRegisterMapping(entity.getKey(), userType))
                    .forEach(entity -> registerHandlerMethod(handler, AopUtils.selectInvocableMethod(entity.getKey(), userType), entity.getValue()));
        }
    }

    private boolean isRegisterMapping(Method method, Class clazz) {
        if (!SimpleCrudControllerSupport.class.isAssignableFrom(clazz)) {
            return true;
        }
        SimpleMapping simpleMapping = (SimpleMapping) clazz.getAnnotation(SimpleMapping.class);
        if (simpleMapping != null) {
            return true;
        }
        simpleMapping = AnnotationUtils.getAnnotation(method, SimpleMapping.class);
        if (simpleMapping != null) {
            return true;
        } else {
            Class superClass = ReflectionUtils.getSuperClass(clazz);
            //检查有没有覆盖父类方法
            try {
                superClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                //没有覆盖 说明是自定义的方法 需要注册
                logger.trace("该方法为基类方法", e);
                return true;
            }
            return false;
        }
    }

}
