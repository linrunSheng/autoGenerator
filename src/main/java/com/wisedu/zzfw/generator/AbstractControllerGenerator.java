package com.wisedu.zzfw.generator;

import com.wisedu.zzfw.GeneratorProperties.Project;
import com.wisedu.zzfw.viewmodel.Clazz;
import com.wisedu.zzfw.viewmodel.ControllerAttribute;
import com.wisedu.zzfw.viewmodel.CrudBean;
import com.wisedu.zzfw.viewmodel.JavaAttribute;

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

	protected abstract String controllerRequestMapping(CrudBean beanModel);

	protected abstract String viewPath(CrudBean beanModel);

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
