package com.wisedu.zzfw.generator.impl;

import java.io.File;
import java.util.List;

import com.wisedu.zzfw.generator.AbstractViewGenerator;
import com.wisedu.zzfw.model.CrudBean;
import com.wisedu.zzfw.model.CrudColumn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter	
public class DefaultJsGenerator extends AbstractViewGenerator {

	String ctx = "${ctx}";
	
	String uniqueColumns;
	
	@Override
	protected String templateName() {
		return "template.js";
	}
	
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected void writeAttribute(CrudBean crudBean) {
		this.uniqueColumns = uniqueColumns();
	}
	
	private String uniqueColumns() {
		List<CrudColumn> columns = this.getColumns();
		String uniqueColumns1 = null;
		StringBuilder sb = new StringBuilder("[");
		for (CrudColumn crudColumn : columns) {
			if (crudColumn.getColumnAttributes().getUniqueable()) {
				sb.append("'").append(crudColumn.getColumnName()).append("',");
			}
		}
		if (sb.length() > 1) {
			uniqueColumns1 = sb.substring(0, sb.length()-1) + "]";
		}else{
			uniqueColumns1 ="[]";
		}
		return uniqueColumns1;
	}

	@Override
	protected File getCodeFile() {
		String projectPath = this.getFileAttribute().getProjectPath();
		String viewPath = this.getControllerAttribute().getViewPath().replaceAll("\\.", "/");
		String filePath = projectPath+ "/src/main/webapp/resources/js/"+ viewPath +".js";
		return new File(filePath);
	}
	
}
