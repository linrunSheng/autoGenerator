package com.wisedu.zzfw.model.setter;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.wisedu.zzfw.viewmodel.CrudColumn;

import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: SetterHelper
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2018年1月4日 下午10:28:02
* @Copyright: Copyright (c) 2017 wisedu
*/
@Component
@Slf4j
public class ColumnExtendPropertiesSetterHelper {
	
	@Autowired(required=false)
	List<ColumnExtendPropertiesSetter> columnAttributeSetter;
	
	public void copyExtendPriperties(CrudColumn model, Map<String,Object> extendAttr,ColumnAttributes configColumnAttributes){
		log.debug("{}扩展属性设置类个数为：{}",model.getColumnName(),columnAttributeSetter==null?0:columnAttributeSetter.size());
		if (CollectionUtils.isEmpty(columnAttributeSetter)) {
			return;
		}
		for (ColumnExtendPropertiesSetter columnAttributeSetter : columnAttributeSetter) {
			columnAttributeSetter.setColumnFieldValue(model, extendAttr, configColumnAttributes);
		}
	}
}
