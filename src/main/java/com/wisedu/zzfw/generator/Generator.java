package com.wisedu.zzfw.generator;

import java.io.File;

import com.wisedu.zzfw.autoconfigation.GeneratorConfigation;
import com.wisedu.zzfw.model.CrudBean;

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
