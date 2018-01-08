package com.lhy.tool.model.binding.impl;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.lhy.tool.model.CrudColumn;
import com.lhy.tool.model.binding.CrudColumnPropertiesBinding;
import com.lhy.tool.util.StringUtil;

import io.swagger.annotations.ApiModelProperty;

/**
* @ClassName: CrudColumnBaseAttrBinding
* @Description: 列基础属性绑定
* @author  luanhy
* @date 2018年1月4日 下午10:13:55
* @Copyright: Copyright (c) 2017 wisedu
*/
@Component
public class CrudColumnBaseAttrBinding implements CrudColumnPropertiesBinding {

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public void binding(CrudColumn columnModel,Field field, ColumnAttributes configColumnAttributes) {
			Id annotation = field.getAnnotation(Id.class);
			if (annotation != null) {
				columnModel.setPrimary(true);
			}
			columnModel.setColumnName(field.getName());
			
			Column column = field.getAnnotation(Column.class);
			if (column == null) {
				columnModel.setDbColumnName(StringUtil.camelToUnderline(field.getName()));
			}else{
				columnModel.setDbColumnName(column.name());
			}
			
			ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
			if (apiModelProperty != null) {
				if(!StringUtils.isEmpty(apiModelProperty.value())){
					columnModel.setColumnDesc(apiModelProperty.value());
				}
				columnModel.setCanNull(!apiModelProperty.required());
			}else{
				columnModel.setColumnDesc(field.getName());
				columnModel.setCanNull(true);
			}
			
			columnModel.setColumnType(field.getType().getName());
		
	}

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public int getOrder() {
		return -1000;
	}

}
