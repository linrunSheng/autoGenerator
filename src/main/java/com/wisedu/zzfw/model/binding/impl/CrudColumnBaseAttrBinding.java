package com.wisedu.zzfw.model.binding.impl;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.wisedu.zzfw.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.wisedu.zzfw.model.CrudColumn;
import com.wisedu.zzfw.model.binding.CrudColumnPropertiesBinding;

import io.swagger.annotations.ApiModelProperty;

/**
* @ClassName: CrudColumnBaseAttrBinding
* @Description: TODO(这里用一句话描述这个类的作用)
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
			
			ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
			Column column = field.getAnnotation(Column.class);
			columnModel.setDbColumnName(column.name());
			if (apiModelProperty != null) {
				if(!StringUtils.isEmpty(apiModelProperty.value())){
					columnModel.setColumnDesc(apiModelProperty.value());
				}
				columnModel.setCanNull(!apiModelProperty.required());
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
