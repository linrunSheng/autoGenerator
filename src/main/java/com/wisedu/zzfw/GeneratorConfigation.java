package com.wisedu.zzfw;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wisedu.zzfw.GeneratorPropertiesWarpper;
import com.wisedu.zzfw.generator.annotation.ModelAttributeAutowired;

import freemarker.template.Configuration;
import lombok.Data;

@Data
@Component
public class GeneratorConfigation {
	
	@Autowired  
	Configuration configuration;
	
	@Autowired
	ModelAttributeAutowired modelAttributeAutowired;
	
	@Autowired
	GeneratorPropertiesWarpper generatorProperties;
	
	GeneratorPropertiesWarpper.Project project;
	
	@PostConstruct
	public void init(){
		initFreeMarkerCongation();
		this.project = generatorProperties.getProject();
	}
	
	private void initFreeMarkerCongation(){
		configuration.setClassForTemplateLoading(this.getClass(), generatorProperties.getTemplatePath());
	}
}
