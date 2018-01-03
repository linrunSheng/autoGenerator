package com.wisedu.zzfw.generator;

import java.io.File;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.JavaAttributes;
import com.wisedu.zzfw.generator.annotation.Model;
import com.wisedu.zzfw.generator.annotation.ModelAttribute;
import com.wisedu.zzfw.generator.annotation.ModelIgnoreAttribute;
import com.wisedu.zzfw.util.StringUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Model
public abstract class JavaGenerator extends AbstractGenerator {
	
	
	//TODO:使用注解来申明别名变量
	
	protected String packageName;
	
	protected String className;
	
	@ModelAttribute("simpleModel")
	protected String modelSimpleName;
	@ModelAttribute("simplePageModel")
	protected String pageModelSimpleName;
	@ModelAttribute("simpleService")
	protected String serviceSimpleName;
	
	@ModelAttribute("model")
	protected String modelFullName;
	@ModelAttribute("pageModel")
	protected String pageModelFullName;
	
	protected String modelPackageName;
	
	@ModelAttribute("service")
	protected String serviceFullName;
	
	@ModelIgnoreAttribute
	protected String servicePackageName;
	
	protected String description;
	
	protected String modelSimpleInstanceName;
	
	protected String pageModelSimpleInstanceName;
	
	protected String serviceSimpleInstanceName;
	@ModelIgnoreAttribute
	protected JavaAttributes javaAttributes;
	
	@ModelAttribute("orderByColumn")
	protected String orderBySql;
	
	protected String columns;
	 		
	@Override
	protected void init() {
		super.init();
		this.javaAttributes = this.modelAttributes.getJavaAttributes();
		this.modelFullName = beanModel.getBeanFullName();
		this.pageModelFullName = modelFullName+"Param";
		this.modelPackageName = javaAttributes.getModelPackage();
		this.servicePackageName = javaAttributes.getServicePackage();
		this.serviceFullName = this.servicePackageName+"."+beanModel.getBeanSimpleName()+"Service";
		
		this.description = beanModel.getBeanDescription();
		
		this.modelSimpleName = beanModel.getBeanSimpleName();
		this.pageModelSimpleName = modelSimpleName+"Param";
		this.serviceSimpleName = modelSimpleName+"Service";

		this.modelSimpleInstanceName= StringUtil.firstToLowerCase(this.getModelSimpleName());
		this.modelSimpleInstanceName= StringUtil.firstToLowerCase(this.getPageModelSimpleName());
		this.serviceSimpleInstanceName= StringUtil.firstToLowerCase(this.getServiceSimpleName());
		
		this.className = className();
		this.packageName = packageName();
		
		this.orderBySql = this.modelAttributes.getPageAttributes().getQueryOrderSql();
	
	}
	
	protected abstract String className();
	
	protected abstract String packageName();
	
	protected File processFile() {
		String packageName = this.getPackageName();
		String filePath = this.projectPath()+ "/src/main/java/"+ packageName.replaceAll("\\.", "/") +"/"+className+".java";
		return new File(filePath);
	}
	

}
