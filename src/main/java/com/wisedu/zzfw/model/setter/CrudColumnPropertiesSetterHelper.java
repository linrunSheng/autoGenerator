package com.wisedu.zzfw.model.setter;

import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.wisedu.zzfw.model.CrudColumn;

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
public class CrudColumnPropertiesSetterHelper {
	
	@Autowired(required=false)
	List<CrudColumnPropertiesSetter> columnAttributeSetter;
	
	@PostConstruct
	public void init() {
		log.debug("属性设置类个数为：{}",columnAttributeSetter==null?0:columnAttributeSetter.size());
	}
	
	public void copyPriperties(CrudColumn model, Field field,ColumnAttributes configColumnAttributes){
		if (CollectionUtils.isEmpty(columnAttributeSetter)) {
			throw new RuntimeException("属性设置类个数为空，请检查");
		}
		for (CrudColumnPropertiesSetter columnAttributeSetter : columnAttributeSetter) {
			columnAttributeSetter.setColumnFieldValue(model, field, configColumnAttributes);
		}
	}
}
