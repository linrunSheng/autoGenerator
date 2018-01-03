package com.wisedu.zzfw.generator.impl;

import com.wisedu.zzfw.generator.JavaGenerator;
import com.wisedu.zzfw.generator.annotation.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Model
public abstract class AbstractControllerGenerator extends JavaGenerator {
	
	private String controllerRequestMapping;
	private String viewPath;
	private String viewFilePrefix;
	
	@Override
	protected void init() {
		super.init();
		this.controllerRequestMapping = controllerRequestMapping();
		this.viewPath = viewPath();
		this.viewFilePrefix = viewFilePrefix();
		
	}
	
	protected abstract String controllerRequestMapping();
	
	protected abstract String viewPath();
	
	protected abstract String viewFilePrefix();

	@Override
	protected String className() {
		return this.modelSimpleName+"Controller";
	}
	
	@Override
	protected String templateName() {
		return "TemplateController.java";
	} 
	
	@Override
	protected String packageName() {
		return this.javaAttributes.getControllerPackage();
	}
	
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected String projectPath() {
		return this.project.getViewProjectPath();
	}

}
