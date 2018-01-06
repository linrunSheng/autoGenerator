package com.lhy.tool.generator.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes.MenuAttributes;
import com.lhy.tool.autoconfigation.GeneratorProperties.Project;
import com.lhy.tool.generator.AbstractGenerator;
import com.lhy.tool.generator.enums.RequestMethodEnum;
import com.lhy.tool.model.ControllerAttribute;
import com.lhy.tool.model.CrudBean;
import com.lhy.tool.model.SqlAttribute;
import com.lhy.tool.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: DefaultSqlGenerator
 * @Description: sql文件创建器
 * @author wutao
 * @date 2017年12月25日 下午6:13:20
 * @Copyright: Copyright (c) 2017 wisedu
 */
@Getter
@Setter
public class DefaultSqlGenerator extends AbstractGenerator {

	SqlAttribute sqlAttribute;

	ControllerAttribute controllerAttribute;

	@Override
	protected final void initGeneratorConfig(CrudBean crudBean) {
		super.initGeneratorConfig(crudBean);
		this.sqlAttribute = this.sqlAttribute(crudBean);
		this.controllerAttribute = this.controllerAttribute(crudBean);
	}

	private SqlAttribute sqlAttribute(CrudBean crudBean) {
		MenuAttributes menuAttributes = crudBean.getModelAttributes().getMenuAttributes();
		return SqlAttribute.builder().moduleWid(UUIDUtil.uuid()).moduleName(menuAttributes.getModuleName())
				.parentModuleName(menuAttributes.getParentModuleName()).menuitemWid(UUIDUtil.uuid())
				.roleName(menuAttributes.getRoleName()).modulepath(modulepath()).build();
	}

	private ControllerAttribute controllerAttribute(CrudBean crudBean) {
		return ControllerAttribute.builder()
				.controllerRequestMapping(controllerRequestMapping(crudBean)).build();
	}

	protected String controllerRequestMapping(CrudBean beanModel) {
		return beanModel.getModelAttributes().getJavaAttributes().getControllerRequestMapping();
	}

	protected List<Map<String, String>> modulepath() {
		List<Map<String, String>> modulepath = new ArrayList<Map<String, String>>();
		for (RequestMethodEnum method : RequestMethodEnum.values()) {
			Map<String, String> content = new HashMap<String, String>();
			content.put("wid", UUIDUtil.uuid());
			content.put("methodName", method.getRequestMethodName());
			modulepath.add(content);
		}
		return modulepath;
	}

	@Override
	protected String templateName() {
		return "template.sql";
	}

	@Override
	protected File getCodeFile() {
		String filePath = this.getFileAttribute().getProjectPath() + "/sql/" + this.getModelName()
				+ ".sql";
		return new File(filePath);
	}

	@Override
	protected String projectPath(Project project) {
		return System.getProperty("user.dir");
	}

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected void writeAttribute(CrudBean crudBean) {
	}

}
