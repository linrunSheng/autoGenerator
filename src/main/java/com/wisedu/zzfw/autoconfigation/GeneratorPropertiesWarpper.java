package com.wisedu.zzfw.autoconfigation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.wisedu.zzfw.model.CrudBean;
import com.wisedu.zzfw.model.factory.CrudBeanFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public class GeneratorPropertiesWarpper extends GeneratorProperties{
	
	@Getter
	@Setter
	List<CrudBean> crudBeanList = new ArrayList<CrudBean>();

	@Autowired
	GeneratorProperties generatorProperties;
	
	@Autowired
	CrudBeanFactory crudBeanFactory;
	
	@SneakyThrows
	@PostConstruct
	private void init(){
		BeanUtils.copyProperties(generatorProperties, this);
		List<ModelAttributes> modelAttributesList = generatorProperties.getModelAttributes();
		for (ModelAttributes modelAttributes : modelAttributesList) {
			CrudBean crudBean = crudBeanFactory.newInstance(modelAttributes);
			this.crudBeanList.add(crudBean);
		}
	}
}
