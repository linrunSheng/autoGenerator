package com.lhy.tool.autoconfigation;

import com.lhy.tool.model.CrudBean;
import com.lhy.tool.model.factory.CrudBeanFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

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
			CrudBean crudBean = crudBeanFactory.newInstance(modelAttributes, generatorProperties.getProject().getJavaProjectPath());
			this.crudBeanList.add(crudBean);
		}
	}
}
