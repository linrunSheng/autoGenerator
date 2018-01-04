package com.wisedu.zzfw.generator;

import java.io.File;

import com.wisedu.zzfw.GeneratorConfigation;
import com.wisedu.zzfw.model.BeanModel;

public interface Generator {
	
	File genCode(BeanModel beanModel, GeneratorConfigation generatorConfigation);
}
