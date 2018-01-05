package com.wisedu.zzfw.model;

import java.lang.reflect.Field;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.wisedu.zzfw.model.setter.CrudColumnPropertiesSetterHelper;
import com.wisedu.zzfw.template.common.util.SpringUtil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@NoArgsConstructor
@ToString
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
	private ColumnAttributes columnAttributes = new ColumnAttributes();
	
}
