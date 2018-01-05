package com.wisedu.zzfw.model.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes;
import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.wisedu.zzfw.model.CrudBean;
import com.wisedu.zzfw.model.CrudColumn;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public abstract class AbstractCrudBeanFactory implements CrudBeanFactory {
	
	@Getter
	@Setter
	CrudColumnFactory crudColumnFactory;

	@Override
	public CrudBean newInstance(ModelAttributes modelAttributes){
		return newInstance(genBeanClass(modelAttributes),modelAttributes);
	}
	
	@SneakyThrows
	protected CrudBean newInstance(Class beanClass, ModelAttributes modelAttributes) {
		CrudBean crudBean = crudBeanClass().newInstance();
		this.initBeanProperties(crudBean, beanClass, modelAttributes);
		this.initBeanCustomProperties(crudBean, modelAttributes);
		return crudBean;
	}
	
	protected abstract Class<? extends CrudBean> crudBeanClass();
	
	protected abstract void initBeanCustomProperties(CrudBean bean, ModelAttributes modelAttributes);

	@SuppressWarnings("rawtypes")
	@SneakyThrows
	private Class genBeanClass(ModelAttributes modelAttributes){
		String className = modelAttributes.getJavaAttributes().getModelPackage()+"."+modelAttributes.getModelName();
		return Class.forName(className);
	}
	
	private void initBeanProperties(CrudBean crudBean, Class beanClass, ModelAttributes modelAttributes){
		crudBean.setSimpleName(beanClass.getSimpleName())
		.setFullName(beanClass.getName())
		.setModelAttributes(modelAttributes);
		ApiModel annotation = (ApiModel) beanClass.getAnnotation(ApiModel.class);
		if (annotation != null) {
			String value = annotation.value();
			crudBean.setDescription(value);
		}
		List<CrudColumn> columns = new ArrayList<CrudColumn>();
		Map<String, ColumnAttributes> configColumns = modelAttributes.getColumnAttrMap();
		Field[] declaredFields = beanClass.getDeclaredFields();
		for (Field field : declaredFields) {
			if(!Modifier.isStatic(field.getModifiers())){	//判断是否静态属性
				CrudColumn newInstance = crudColumnFactory.newInstance(field, configColumns.get(field.getName()));
				columns.add(newInstance);
			}
		}
		crudBean.setColumns(columns);
	}
	
	

}
