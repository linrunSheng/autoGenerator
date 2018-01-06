package com.lhy.tool.generator;

import java.io.File;

import com.lhy.tool.autoconfigation.GeneratorConfigation;
import com.lhy.tool.model.CrudBean;

public interface Generator {
	
	/**
	 * 
	* @Title: genCode
	* @Description: 生成增删改查代码
	* @param crudBean
	* @param generatorConfigation
	* @return
	 */
	File genCode(CrudBean crudBean, GeneratorConfigation generatorConfigation);
}
