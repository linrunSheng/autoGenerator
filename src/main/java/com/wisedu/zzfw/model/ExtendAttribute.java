package com.wisedu.zzfw.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: ExtendAttribute
* @Description:额外扩展属性
* @author  luanhy
* @date 2018年1月6日 下午2:58:48
* @Copyright: Copyright (c) 2017 wisedu
*/
@Getter
@Setter
public class ExtendAttribute {
	
	/**
	 * 模型bean扩展属性
	 */
	Map<String, Object> beanExtendAttrMap = new HashMap<String, Object>();
	
	/**
	 * 列扩展属性
	 *  key 列名       value 属性键值对
	 */
	Map<String, Map<String, Object>> columnExtendAttrMap = new HashMap<String, Map<String,Object>>();
}
