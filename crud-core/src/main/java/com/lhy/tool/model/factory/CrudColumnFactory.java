package com.lhy.tool.model.factory;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.lhy.tool.model.CrudColumn;

import java.lang.reflect.Field;

public interface CrudColumnFactory {
	
	CrudColumn newInstance(Field field, ColumnAttributes columnAttributes);
	
}
