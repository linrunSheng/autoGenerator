package com.wisedu.zzfw.generator;

import java.io.File;

import com.wisedu.zzfw.autoconfigation.GeneratorProperties.ModelAttributes.JavaAttributes;
import com.wisedu.zzfw.model.Clazz;
import com.wisedu.zzfw.model.CrudBean;
import com.wisedu.zzfw.model.JavaAttribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractJavaGenerator extends AbstractGenerator {

	JavaAttribute javaAttribute;

	@Override
	protected void initGeneratorConfig(CrudBean crudBean) {
		super.initGeneratorConfig(crudBean);
		this.javaAttribute = javaAttribute(crudBean);
	}

	private JavaAttribute javaAttribute(CrudBean crudBean) {
		JavaAttributes javaAttributes = crudBean.getModelAttributes().getJavaAttributes();
		String modelFullName = crudBean.getFullName();
		String modelSimpleName = crudBean.getSimpleName();
		return JavaAttribute.builder().description(crudBean.getDescription())
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
	protected File getCodeFile() {
		Clazz curClazz = this.curClazz(this.javaAttribute);
		String packageName = curClazz.getPackageName();
		String filePath = this.fileAttribute.getProjectPath() + "/src/main/java/" + packageName.replaceAll("\\.", "/")
				+ "/" + curClazz.getName() + ".java";
		return new File(filePath);
	}

}
