package com.wisedu.zzfw.custom;

import java.lang.reflect.Field;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.wisedu.zzfw.model.CrudColumn;
import com.wisedu.zzfw.model.factory.AbstractCrudColumnFactory;

public class CustomCrudColumnFactory extends AbstractCrudColumnFactory {

	@Override
	protected Class<? extends CrudColumn> crudColumnClass() {
		return CustomCrudColumn.class;
	}

	@Override
	protected void initColumnCustomProperties(CrudColumn column, Field field, ColumnAttributes columnAttributes) {
	}



}
