package com.wisedu.zzfw.generator;

import java.io.File;
import java.util.Map;

import com.wisedu.zzfw.GeneratorProperties.Project;
import com.wisedu.zzfw.model.ControllerAttribute;
import com.wisedu.zzfw.model.CrudBean;

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
	
	protected ControllerAttribute controllerAttribute;
	
	protected String modelName;
	
	@Override
	protected void init(CrudBean crudBean) {
		super.init(crudBean);
		this.initControllerAttribute(crudBean);
	}
		
	protected void initControllerAttribute(CrudBean beanModel){
		this.controllerAttribute = ControllerAttribute.builder().controllerRequestMapping(controllerRequestMapping(beanModel))
				.viewPath(viewPath(beanModel)).build();
		this.modelName = beanModel.getModelAttributes().getModelName();
	}

	protected String controllerRequestMapping(CrudBean beanModel) {
		return beanModel.getModelAttributes().getJavaAttributes().getControllerRequestMapping();
	}
	
	protected String viewPath(CrudBean beanModel) {
		return beanModel.getModelAttributes().getViewAttributes().getViewPath();
	}
	
	@Override
	protected void addExtractModelAttribute(Map<String, Object> model) {
	}
	
	@Override
	protected String projectPath(Project project) {
		return project.getViewProjectPath();
	}
	
}
