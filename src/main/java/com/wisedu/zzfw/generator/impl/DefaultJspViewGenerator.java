package com.wisedu.zzfw.generator.impl;

import java.io.File;

import com.wisedu.zzfw.generator.AbstractViewGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultJspViewGenerator extends AbstractViewGenerator {
	
	private String ctx = "${ctx}";
	
	@Override
	protected String templateName() {
		return "template.jsp";
	}

	@Override
	protected File getFile() {
		String projectPath = this.getFileAttribute().getProjectPath();
		String viewPath = this.getControllerAttribute().getViewPath().replaceAll("\\.", "/");
		String filePath = projectPath+ "/src/main/webapp/WEB-INF/views/"+ viewPath +".jsp";
		return new File(filePath);
	}

	
}

