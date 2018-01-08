package com.lhy.tool.model.factory;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes;
import com.lhy.tool.model.CrudBean;

public interface CrudBeanFactory {
	
	CrudBean newInstance(ModelAttributes modelAttributes);
	
}
