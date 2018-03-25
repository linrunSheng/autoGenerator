package com.lhy.tool.model.factory.impl;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes;
import com.lhy.tool.model.CrudBean;
import com.lhy.tool.model.CrudColumn;
import com.lhy.tool.model.factory.AbstractCrudBeanFactory;

import java.util.Comparator;

public class DefaultCrudBeanFactory extends AbstractCrudBeanFactory {
	
	protected Class<? extends CrudBean> crudBeanClass(){
		return CrudBean.class;
	}

	@Override
	protected void initBeanCustomProperties(CrudBean bean, ModelAttributes modelAttributes) {
	}

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected Comparator<CrudColumn> columnsComparator() {
		return null;
	}
	

}
