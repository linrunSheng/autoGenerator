package com.wisedu.zzfw.generator.impl;

import com.wisedu.zzfw.generator.AbstractControllerGenerator;
import com.wisedu.zzfw.generator.annotation.Model;
import com.wisedu.zzfw.viewmodel.CrudBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Model
public class DefaultControllerGenerator extends AbstractControllerGenerator {
	
	@Override
	protected String controllerRequestMapping(CrudBean beanModel) {
		return beanModel.getModelAttributes().getJavaAttributes().getControllerRequestMapping();
	}
	
	@Override
	protected String viewPath(CrudBean beanModel) {
		return beanModel.getModelAttributes().getViewAttributes().getViewPath();
	}

}

