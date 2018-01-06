package com.lhy.tool.model.factory.impl;

import java.lang.reflect.Field;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.lhy.tool.model.CrudColumn;
import com.lhy.tool.model.factory.AbstractCrudColumnFactory;

public class DefaultCrudColumnFactory extends AbstractCrudColumnFactory {

	@Override
	protected Class<? extends CrudColumn> crudColumnClass() {
		return CrudColumn.class;
	}

	@Override
	protected void initColumnCustomProperties(CrudColumn column, Field field, ColumnAttributes columnAttributes) {
	}
	

}
