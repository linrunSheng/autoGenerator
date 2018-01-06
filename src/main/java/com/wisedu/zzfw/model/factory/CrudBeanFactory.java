package com.wisedu.zzfw.model.factory;

import com.wisedu.zzfw.autoconfigation.GeneratorProperties.ModelAttributes;
import com.wisedu.zzfw.model.CrudBean;

public interface CrudBeanFactory {
	
	CrudBean newInstance(ModelAttributes modelAttributes);
	
}
