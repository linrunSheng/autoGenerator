package com.lhy.tool.generator.impl;

import com.lhy.tool.generator.AbstractServiceGenerator;
import com.lhy.tool.model.CrudBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultServiceGenerator extends AbstractServiceGenerator {

	@Override
	protected String queryOrderSql(CrudBean crudBean) {
		return crudBean.getModelAttributes().getJavaAttributes().getQueryOrderSql();
	}

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected void writeAttribute(CrudBean crudBean) {
		
	}

}

