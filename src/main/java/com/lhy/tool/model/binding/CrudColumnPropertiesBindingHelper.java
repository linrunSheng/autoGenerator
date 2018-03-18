package com.lhy.tool.model.binding;

import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.lhy.tool.model.CrudColumn;

import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: CrudColumnPropertiesBindingHelper
* @Description: 设置crudlColumn属性
* @author  luanhy
* @date 2018年1月4日 下午10:28:02
* @Copyright: Copyright (c) 2017 wisedu
*/
@Component
@Slf4j
public class CrudColumnPropertiesBindingHelper {
	
	@Autowired(required=false)
	List<CrudColumnPropertiesBinding> columnAttributeBinding;
	
	@PostConstruct
	public void init() {
		log.debug("属性设置类个数为：{}",columnAttributeBinding==null?0:columnAttributeBinding.size());
	}
	
	public void binding(CrudColumn model, Field field,ColumnAttributes configColumnAttributes){
		if (CollectionUtils.isEmpty(columnAttributeBinding)) {
			throw new RuntimeException("属性设置类个数为空，请检查");
		}
		columnAttributeBinding.forEach(crudColumnPropertiesBinding -> 	crudColumnPropertiesBinding.binding(model, field, configColumnAttributes));
		//for (CrudColumnPropertiesBinding columnAttributeSetter : columnAttributeBinding) {
			//columnAttributeSetter.binding(model, field, configColumnAttributes)
		//}
	}
}
