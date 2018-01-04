package com.wisedu.zzfw.viewmodel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes;
import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.ColumnAttributes;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
public class CrudBean {
	
	private String beanDescription;
	
	private String beanSimpleName;
	
	private String beanFullName;
	
	private List<CrudColumn> columns;
	
	private ModelAttributes modelAttributes;
	
	public CrudBean(Class<?> beanClass, ModelAttributes modelAttributes) {
		this.beanSimpleName = beanClass.getSimpleName();
		this.beanFullName = beanClass.getName();
		this.modelAttributes = modelAttributes;
		ApiModel annotation = beanClass.getAnnotation(ApiModel.class);
		if (annotation != null) {
			String value = annotation.value();
			this.beanDescription = value;
		}
		Map<String, ColumnAttributes> configColumns = modelAttributes.getColumnAttrMap();
		Field[] declaredFields = beanClass.getDeclaredFields();
		for (Field field : declaredFields) {
			if(!Modifier.isStatic(field.getModifiers())){	//判断是否静态属性
				columns.add(new CrudColumn(field ,configColumns.get(field.getName())));
			}
		}
	}

}
