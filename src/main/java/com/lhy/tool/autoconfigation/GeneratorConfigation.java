package com.lhy.tool.autoconfigation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import freemarker.template.Configuration;
import lombok.Data;

@Data
public class GeneratorConfigation {
	
	@Autowired  
	Configuration freeMarkerconfiguration;
	
	@Autowired
	GeneratorPropertiesWarpper generatorProperties;
	
	GeneratorPropertiesWarpper.Project project;
	
	@PostConstruct
	public void init(){
		initFreeMarkerCongation();
		this.project = generatorProperties.getProject();
	}
	
	private void initFreeMarkerCongation(){
		freeMarkerconfiguration.setClassForTemplateLoading(this.getClass(), generatorProperties.getTemplatePath());
	}
}
