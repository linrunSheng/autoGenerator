package com.wisedu.zzfw.model.factory.impl;

import com.wisedu.zzfw.autoconfigation.GeneratorProperties.ModelAttributes;
import com.wisedu.zzfw.model.CrudBean;
import com.wisedu.zzfw.model.factory.AbstractCrudBeanFactory;

public class DefaultCrudBeanFactory extends AbstractCrudBeanFactory {
	
	protected Class<? extends CrudBean> crudBeanClass(){
		return CrudBean.class;
	}

	@Override
	protected void initBeanCustomProperties(CrudBean bean, ModelAttributes modelAttributes) {
	}
		

}
