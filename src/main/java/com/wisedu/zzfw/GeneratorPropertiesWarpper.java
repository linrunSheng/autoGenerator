package com.wisedu.zzfw;

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
	private List<CrudBean> crudBeanList = new ArrayList<CrudBean>();

	@Autowired
	private GeneratorProperties generatorProperties;
	
	@Autowired
	CrudBeanFactory crudBeanFactory;
	
	@SneakyThrows
	@PostConstruct
	private void init(){
		BeanUtils.copyProperties(generatorProperties, this);
		List<ModelAttributes> modelAttributesList = generatorProperties.getModelAttributes();
		for (ModelAttributes modelAttributes2 : modelAttributesList) {
			CrudBean crudBean = crudBeanFactory.newInstance(modelAttributes2);
			this.crudBeanList.add(crudBean);
		}
	}
}
