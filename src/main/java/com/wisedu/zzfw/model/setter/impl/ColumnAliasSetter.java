package com.wisedu.zzfw.model.setter.impl;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.wisedu.zzfw.model.setter.ColumnPropertiesSetter;
import com.wisedu.zzfw.viewmodel.CrudColumn;

/**
* @ClassName: AliasColumnAttributeSetter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2018年1月4日 下午10:13:55
* @Copyright: Copyright (c) 2017 wisedu
*/
@Component
public class ColumnAliasSetter implements ColumnPropertiesSetter {

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public void setColumnFieldValue(CrudColumn columnModel,Field field, ColumnAttributes configColumnAttributes) {
		if (configColumnAttributes == null) {
			return;
		}
		String columnDesc = columnModel.getColumnDesc();
		String alias = configColumnAttributes.getAlias();
		if (!StringUtils.isEmpty(alias)) {
			columnModel.setColumnDesc(alias);
		}else{
			if (StringUtils.isEmpty(columnDesc)) {
				columnModel.setColumnDesc(columnModel.getColumnName());
			}else{
			}
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
