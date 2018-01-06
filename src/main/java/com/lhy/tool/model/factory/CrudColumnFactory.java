package com.lhy.tool.model.factory;

import java.lang.reflect.Field;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.lhy.tool.model.CrudColumn;

public interface CrudColumnFactory {
	
	CrudColumn newInstance(Field field,ColumnAttributes columnAttributes);
	
}
