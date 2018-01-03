package com.wisedu.zzfw.generator.impl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultControllerGenerator extends AbstractControllerGenerator {
	
	
	@Override
	protected String controllerRequestMapping() {
		return this.javaAttributes.getControllerPath();
	}
	
	@Override
	protected String viewPath() {
		return this.modelAttributes.getPageAttributes().getJspPath();
	}

	@Override
	protected String viewFilePrefix() {
		return this.beanModel.getBeanSimpleName().toLowerCase();
	}
	
}

