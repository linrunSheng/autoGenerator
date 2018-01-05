package com.wisedu.zzfw.custom;

import com.wisedu.zzfw.generator.AbstractControllerGenerator;
import com.wisedu.zzfw.generator.annotation.Model;
import com.wisedu.zzfw.model.CrudBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Model
public class CustomControllerGenerator extends AbstractControllerGenerator {
	
	@Override
	protected String controllerRequestMapping(CrudBean beanModel) {
		return beanModel.getModelAttributes().getJavaAttributes().getControllerRequestMapping();
	}
	
	@Override
	protected String viewPath(CrudBean beanModel) {
		return beanModel.getModelAttributes().getViewAttributes().getViewPath();
	}

}

