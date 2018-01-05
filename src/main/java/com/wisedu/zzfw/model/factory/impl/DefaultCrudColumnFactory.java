package com.wisedu.zzfw.model.factory.impl;

import java.lang.reflect.Field;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.wisedu.zzfw.model.CrudColumn;
import com.wisedu.zzfw.model.factory.AbstractCrudColumnFactory;

public class DefaultCrudColumnFactory extends AbstractCrudColumnFactory {

	@Override
	protected Class<? extends CrudColumn> crudColumnClass() {
		return CrudColumn.class;
	}

	@Override
	protected void initColumnCustomProperties(CrudColumn column, Field field, ColumnAttributes columnAttributes) {
	}
	

}
