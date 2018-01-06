package com.lhy.tool.model.factory.impl;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes;
import com.lhy.tool.model.CrudBean;
import com.lhy.tool.model.factory.AbstractCrudBeanFactory;

public class DefaultCrudBeanFactory extends AbstractCrudBeanFactory {
	
	protected Class<? extends CrudBean> crudBeanClass(){
		return CrudBean.class;
	}

	@Override
	protected void initBeanCustomProperties(CrudBean bean, ModelAttributes modelAttributes) {
	}
		

}
