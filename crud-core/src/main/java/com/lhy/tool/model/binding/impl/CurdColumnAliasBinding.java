package com.lhy.tool.model.binding.impl;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.lhy.tool.model.CrudColumn;
import com.lhy.tool.model.binding.CrudColumnPropertiesBinding;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
* @ClassName: CurdColumnAliasBinding
* @Description: 列别名绑定
* @author  luanhy
* @date 2018年1月4日 下午10:13:55
* @Copyright: Copyright (c) 2017 wisedu
*/
@Component
public class CurdColumnAliasBinding implements CrudColumnPropertiesBinding {

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public void binding(CrudColumn columnModel,Field field, ColumnAttributes configColumnAttributes) {
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
