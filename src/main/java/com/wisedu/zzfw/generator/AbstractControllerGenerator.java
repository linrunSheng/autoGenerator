package com.wisedu.zzfw.generator;

import com.wisedu.zzfw.GeneratorProperties.Project;
import com.wisedu.zzfw.model.Clazz;
import com.wisedu.zzfw.model.ControllerAttribute;
import com.wisedu.zzfw.model.CrudBean;
import com.wisedu.zzfw.model.JavaAttribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractControllerGenerator extends AbstractJavaGenerator {

	protected ControllerAttribute controllerAttribute;

	@Override
	protected void init(CrudBean beanModel) {
		super.init(beanModel);
		this.initControllerAttribute(beanModel);
	}
	
	protected void initControllerAttribute(CrudBean beanModel){
		this.controllerAttribute = ControllerAttribute.builder().controllerRequestMapping(controllerRequestMapping(beanModel))
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
