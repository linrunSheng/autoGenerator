package com.lhy.tool.autoconfigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import com.lhy.tool.model.factory.CrudBeanFactory;
import com.lhy.tool.model.factory.CrudColumnFactory;
import com.lhy.tool.model.factory.impl.DefaultCrudBeanFactory;
import com.lhy.tool.model.factory.impl.DefaultCrudColumnFactory;

import lombok.SneakyThrows;

public class CrudFactoryAutoConfigation {

	@Autowired
	GeneratorPropertiesWarpper generatorPropertiesWarpper;

	@Bean
	@ConditionalOnMissingBean(CrudBeanFactory.class)
	@SneakyThrows
	public CrudBeanFactory crudBeanFactory(CrudColumnFactory crudColumnFactory) {
		return new DefaultCrudBeanFactory().setCrudColumnFactory(crudColumnFactory);
	}
	
	@Bean
	@SneakyThrows
	@ConditionalOnMissingBean(CrudColumnFactory.class)
	public CrudColumnFactory crudColumnFactory() {
		return new DefaultCrudColumnFactory();
	}
}
