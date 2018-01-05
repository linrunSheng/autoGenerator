package com.wisedu.zzfw.generator.impl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wisedu.zzfw.generator.AbstractViewGenerator;
import com.wisedu.zzfw.model.CrudBean;
import com.wisedu.zzfw.model.CrudColumn;

@Component
public class DefaultJsGenerator extends AbstractViewGenerator {

	String ctx = "${ctx}";
	
	String uniqueColumns;
	
	@Override
	protected String templateName() {
		return "template.js";
	}
	
	protected void initControllerAttribute(CrudBean beanModel){
		super.initControllerAttribute(beanModel);
		this.initUniqueColumns();
	}
	
	private void initUniqueColumns() {
		List<CrudColumn> columns = this.getColumns();
		StringBuilder sb = new StringBuilder("[");
		for (CrudColumn crudColumn : columns) {
			if (crudColumn.getColumnAttributes().getUniqueable()) {
				sb.append("'").append(crudColumn.getColumnName()).append("',");
			}
		}
		if (sb.length() > 1) {
			uniqueColumns = sb.substring(0, sb.length()-1) + "]";
		}else{
			uniqueColumns +="]";
		}
	}

	@Override
	protected File getFile() {
		String projectPath = this.getFileAttribute().getProjectPath();
		String viewPath = this.getControllerAttribute().getViewPath().replaceAll("\\.", "/");
		String filePath = projectPath+ "/src/main/webapp/resources/js/"+ viewPath +".js";
		return new File(filePath);
	}
	
}
