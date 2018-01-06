package com.lhy.tool.generator;

import com.lhy.tool.autoconfigation.GeneratorProperties.Project;
import com.lhy.tool.model.Clazz;
import com.lhy.tool.model.CrudBean;
import com.lhy.tool.model.JavaAttribute;

public abstract class AbstractPageModelGenerator extends AbstractJavaGenerator{


	@Override
	protected final void initGeneratorConfig(CrudBean crudBean) {
		super.initGeneratorConfig(crudBean);
	}
	
	@Override
	protected Clazz curClazz(JavaAttribute javaAttribute) {
		return javaAttribute.getPageModel();
	}

	@Override
	protected String projectPath(Project project) {
		return project.getJavaProjectPath();
	}

	@Override
	protected String templateName() {
		return "TemplatePageModelParam.java";
	}

}
