package com.wisedu.zzfw.model.factory;

import java.lang.reflect.Field;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.wisedu.zzfw.model.CrudColumn;

public interface CrudColumnFactory {
	
	CrudColumn newInstance(Field field,ColumnAttributes columnAttributes);
	
}
