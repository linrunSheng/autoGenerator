package com.wisedu.zzfw.generator.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

@Component  
public class ModelAttributeAutowired {
	
	public void setModelAttribute(Map<String,Object> modelMap, Object object){
		if (object.getClass().getAnnotation(Model.class) != null) {
			Field[] declaredFields = object.getClass().getDeclaredFields();
			for (Field field : declaredFields) {
				//排除忽略字段
				ModelIgnoreAttribute modelIgnoreAttribute = field.getAnnotation(ModelIgnoreAttribute.class);
				if (modelIgnoreAttribute != null) {
					if(Modifier.isStatic(field.getModifiers())){
						continue;
					}
					ModelAttribute annotation = field.getAnnotation(ModelAttribute.class);
					if (annotation == null) {
						modelMap.put(field.getName(), getFieldValue(field, object));
					}else{
						String key = field.getName();
						if (!StringUtils.isEmpty(annotation.value())) {
							key = annotation.value();
						}
						modelMap.put(key, getFieldValue(field, object));
					}
				}
			}
		}
	}
	
	public Object getFieldValue(Field field ,Object generator){
		Object value = ReflectionUtils.getField(field, generator);
		//基本数据类型
		if (value.getClass().isPrimitive()) {
			return value;
		}
		return value;
	}
}
