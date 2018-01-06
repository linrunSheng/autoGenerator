package com.wisedu.zzfw.generator;

import com.wisedu.zzfw.autoconfigation.GeneratorProperties.Project;
import com.wisedu.zzfw.model.Clazz;
import com.wisedu.zzfw.model.CrudBean;
import com.wisedu.zzfw.model.JavaAttribute;
import com.wisedu.zzfw.model.ServiceAttribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractServiceGenerator extends AbstractJavaGenerator {

	ServiceAttribute serviceAttribute;

	@Override
	protected final void initGeneratorConfig(CrudBean crudBean) {
		super.initGeneratorConfig(crudBean);
		this.serviceAttribute = this.serviceAttribute(crudBean);
	}

	private ServiceAttribute serviceAttribute(CrudBean crudBean) {
		return ServiceAttribute.builder()
				.orderBySql(queryOrderSql(crudBean)).build();
	}
	
	protected abstract String queryOrderSql(CrudBean crudBean);

	@Override
	protected Clazz curClazz(JavaAttribute javaAttribute) {
		return javaAttribute.getService();
	}

	@Override
	protected String projectPath(Project project) {
		return project.getJavaProjectPath();
	}

	@Override
	protected String templateName() {
		return "TemplateService.java";
	}
}
