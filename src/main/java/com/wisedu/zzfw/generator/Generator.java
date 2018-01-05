package com.wisedu.zzfw.generator;

import java.io.File;

import com.wisedu.zzfw.GeneratorConfigation;
import com.wisedu.zzfw.model.CrudBean;

public interface Generator {
	
	File genCode(CrudBean beanModel, GeneratorConfigation generatorConfigation);
}
