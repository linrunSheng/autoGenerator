package com.lhy.tool.generator;

import com.lhy.tool.autoconfigation.GeneratorProperties.Project;
import com.lhy.tool.model.ControllerAttribute;
import com.lhy.tool.model.CrudBean;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: AbstractViewGenerator
 * @Description: 视图生成器
 * @author  wutao
 * @date 2017年12月21日 上午9:33:33
 * @Copyright: Copyright (c) 2017 wisedu
 */
@Getter
@Setter
public abstract class AbstractViewGenerator extends AbstractGenerator{
	
	ControllerAttribute controllerAttribute;
	
	@Override
	protected final void initGeneratorConfig(CrudBean crudBean) {
		super.initGeneratorConfig(crudBean);
		this.controllerAttribute = this.controllerAttribute(crudBean);
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
	protected String projectPath(Project project) {
		return project.getViewProjectPath();
	}
	
}
