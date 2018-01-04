package com.wisedu.zzfw.model.setter.impl;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.wisedu.zzfw.model.setter.ColumnPropertiesSetter;
import com.wisedu.zzfw.viewmodel.CrudColumn;

/**
* @ClassName: AlaisColumnAttributeSetter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2018年1月4日 下午10:13:55
* @Copyright: Copyright (c) 2017 wisedu
*/
@Component
public class ColumnNullableSetter implements ColumnPropertiesSetter {

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public void setColumnFieldValue(CrudColumn columnModel,Field field, ColumnAttributes configColumnAttributes) {
		if (configColumnAttributes == null) {
			return;
		}
		boolean canNull = columnModel.getCanNull();
		if (canNull) {
			boolean nullable = configColumnAttributes.getNullable();
			columnModel.setCanNull(nullable);
		}
		
	}

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public int getOrder() {
		return -999;
	}

}
