package com.wisedu.zzfw.generator;

import java.util.Map;

import com.wisedu.zzfw.GeneratorProperties.Project;
import com.wisedu.zzfw.model.Clazz;
import com.wisedu.zzfw.model.CrudBean;
import com.wisedu.zzfw.model.JavaAttribute;
import com.wisedu.zzfw.model.ServiceAttribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractServiceGenerator extends AbstractJavaGenerator {

	protected ServiceAttribute serviceAttribute;

	@Override
	protected void init(CrudBean crudBean) {
		super.init(crudBean);
		this.initServiceAttribute(crudBean);
	}

	protected void initServiceAttribute(CrudBean crudBean) {
		this.serviceAttribute = ServiceAttribute.builder()
				.orderBySql(queryOrderSql(crudBean)).build();
	}
	
	protected abstract String queryOrderSql(CrudBean crudBean);


	@Override
	protected void addExtractModelAttribute(Map<String, Object> model) {
	}

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
