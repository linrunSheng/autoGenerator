package com.wisedu.zzfw.generator;

import com.wisedu.zzfw.autoconfigation.GeneratorProperties.Project;
import com.wisedu.zzfw.model.Clazz;
import com.wisedu.zzfw.model.CrudBean;
import com.wisedu.zzfw.model.JavaAttribute;

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
