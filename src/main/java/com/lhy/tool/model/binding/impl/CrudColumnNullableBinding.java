package com.lhy.tool.model.binding.impl;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.lhy.tool.model.CrudColumn;
import com.lhy.tool.model.binding.CrudColumnPropertiesBinding;

/**
* @ClassName: CrudColumnNullableBinding
* @Description: 列空置绑定
* @author  luanhy
* @date 2018年1月4日 下午10:13:55
* @Copyright: Copyright (c) 2017 wisedu
*/
@Component
public class CrudColumnNullableBinding implements CrudColumnPropertiesBinding {

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public void binding(CrudColumn columnModel,Field field, ColumnAttributes configColumnAttributes) {
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
