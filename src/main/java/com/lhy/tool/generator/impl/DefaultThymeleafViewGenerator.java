package com.lhy.tool.generator.impl;

import java.io.File;

import com.lhy.tool.generator.AbstractViewGenerator;
import com.lhy.tool.model.CrudBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultThymeleafViewGenerator extends AbstractViewGenerator {

	@Override
	protected String templateName() {
		return "template.html";
	}

	@Override
	protected File getCodeFile() {
		String projectPath = this.getFileAttribute().getProjectPath();
		String viewPath = this.getControllerAttribute().getViewPath().replaceAll("\\.", "/");
		String filePath = projectPath + "/src/main/resources/templates/" + viewPath + ".html";
		return new File(filePath);
	}

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected void writeAttribute(CrudBean crudBean) {
	}

}
