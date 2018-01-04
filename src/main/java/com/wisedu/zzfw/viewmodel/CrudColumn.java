package com.wisedu.zzfw.viewmodel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.wisedu.zzfw.model.setter.ColumnExtendPropertiesSetterHelper;
import com.wisedu.zzfw.model.setter.ColumnPropertiesSetterHelper;
import com.wisedu.zzfw.template.common.util.SpringUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * 
* @ClassName: ColumnModel
* @Description: 从model类获取的属性
* @author  luanhy
* @date 2018年1月4日 下午10:00:48
* @Copyright: Copyright (c) 2017 wisedu
 */
@Setter
@Getter
public class CrudColumn {
	
	/**
	 * 是否主键
	 */
	private boolean primary;
	
	/**
	 * 列java属性名
	 */
	private String columnName;
	
	/**
	 * 列描述
	 */
	private String columnDesc;
	
	/**
	 * 对应数据库字段名
	 */
	private String dbColumnName;
	
	/**
	 * 是否可空
	 */
	private boolean canNull;
	
	/**
	 * 列java类型
	 */
	private String columnType;
	
	/**
	 * 从配置获取列属性
	 */
	private ColumnAttributes columnAttributes;
	
	private Map<String,Object> extendAttributes = new HashMap<String,Object>();
	
	public CrudColumn(Field field, ColumnAttributes configColumnAttributes) {
		this.columnAttributes = configColumnAttributes;
		copyPriperties(field, configColumnAttributes);
		copyExtendPriperties(extendAttributes, configColumnAttributes);
	}
	
	public void copyPriperties(Field field, ColumnAttributes configColumnAttributes){
		ColumnPropertiesSetterHelper setterHelper = SpringUtil.getBean(ColumnPropertiesSetterHelper.class);
		setterHelper.copyPriperties(this, field, configColumnAttributes);
	}
	
	public void copyExtendPriperties(Map<String,Object> extendAttributes, ColumnAttributes configColumnAttributes){
		ColumnExtendPropertiesSetterHelper setterHelper = SpringUtil.getBean(ColumnExtendPropertiesSetterHelper.class);
		setterHelper.copyExtendPriperties(this, extendAttributes, configColumnAttributes);
	}
}
