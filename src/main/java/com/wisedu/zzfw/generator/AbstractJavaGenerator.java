package com.wisedu.zzfw.generator;

import java.io.File;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.JavaAttributes;
import com.wisedu.zzfw.model.BeanModel;
import com.wisedu.zzfw.viewmodel.Clazz;
import com.wisedu.zzfw.viewmodel.JavaAttribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractJavaGenerator extends AbstractGenerator {

	protected JavaAttribute javaAttribute;

	@Override
	protected void init(BeanModel beanModel) {
		super.init(beanModel);
		this.initJavaAttribute(beanModel);
	}

	protected void initJavaAttribute(BeanModel beanModel) {
		JavaAttributes javaAttributes = beanModel.getModelAttributes().getJavaAttributes();
		String modelFullName = beanModel.getBeanFullName();
		String modelSimpleName = beanModel.getBeanSimpleName();
		this.javaAttribute = JavaAttribute.builder().description(beanModel.getBeanDescription())
				.model(new Clazz(modelFullName)).pageModel(new Clazz(modelFullName + "Param"))
				.service(new Clazz(javaAttributes.getServicePackage() + "." + modelSimpleName + "Service"))
				.controller(
						new Clazz(javaAttributes.getControllerPackage() + "." + modelSimpleName + "Controller"))
				.build();
	}

	/**
	 * 
	 * @Title: curClzz
	 * @Description: 当前需要生成的类
	 * @return Clazz 返回类型
	 * @return
	 */
	protected abstract Clazz curClazz(JavaAttribute javaAttribute);
	
	/**
	 * 生成的代码文件
	 */
	protected File getFile() {
		Clazz curClazz = this.curClazz(this.javaAttribute);
		String packageName = curClazz.getPackageName();
		String filePath = this.fileAttribute.getProjectPath() + "/src/main/java/" + packageName.replaceAll("\\.", "/")
				+ "/" + curClazz.getName() + ".java";
		return new File(filePath);
	}

}
