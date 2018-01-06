package com.lhy.tool.generator;

import com.lhy.tool.autoconfigation.GeneratorProperties.Project;
import com.lhy.tool.model.Clazz;
import com.lhy.tool.model.ControllerAttribute;
import com.lhy.tool.model.CrudBean;
import com.lhy.tool.model.JavaAttribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractControllerGenerator extends AbstractJavaGenerator {

	ControllerAttribute controllerAttribute;

	@Override
	protected final void initGeneratorConfig(CrudBean beanModel) {
		super.initGeneratorConfig(beanModel);
		this.controllerAttribute = this.controllerAttribute(beanModel);
	}
	
	private ControllerAttribute controllerAttribute(CrudBean beanModel){
		return ControllerAttribute.builder().controllerRequestMapping(controllerRequestMapping(beanModel))
				.viewPath(viewPath(beanModel)).build();
	}
	
	protected String controllerRequestMapping(CrudBean beanModel) {
		return beanModel.getModelAttributes().getJavaAttributes().getControllerRequestMapping();
	}
	
	protected String viewPath(CrudBean beanModel) {
		return beanModel.getModelAttributes().getViewAttributes().getViewPath();
	}

	@Override
	protected final Clazz curClazz(JavaAttribute javaAttribute) {
		return javaAttribute.getController();
	}

	@Override
	protected String templateName() {
		return "TemplateController.java";
	}

	@Override
	protected String projectPath(Project project) {
		return project.getViewProjectPath();
	}

}
