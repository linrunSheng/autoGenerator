package com.wisedu.zzfw.generator;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.wisedu.zzfw.GeneratorProperties;
import com.wisedu.zzfw.GeneratorProperties.ModelAttributes;
import com.wisedu.zzfw.GeneratorProperties.Project;
import com.wisedu.zzfw.generator.annotation.ModelAttributeAutowired;
import com.wisedu.zzfw.model.BeanModel;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public abstract class AbstractGenerator implements Generator {
	
	@Autowired  
	private Configuration configuration;
	
	/**
	 * 项目路径  包含后端项目路径和前端页面项目路径
	 */
	protected Project project;
	/**
	 * mbg生成的java模型类
	 */
	protected BeanModel beanModel; 
	
	/**
	 * 模型额外属性
	 */
	protected ModelAttributes modelAttributes;

	/**
	 * 生成的代码文件
	 */
	protected File codeFile;
	
	protected String projectPath;
	
	/**
	 * 例如 TemplateController.java
	 */
	protected String templateName;
	
	@Autowired
	ModelAttributeAutowired modelAttributeAutowired;
	
	@Autowired
	private void setProject(@Qualifier("generatorProperties") GeneratorProperties generatorProperties){
		this.project = generatorProperties.getProject();
	}
	
	protected void init(){
		initFreeMarkerCongation();
		this.projectPath = this.projectPath();
		this.templateName = this.templateName();
	}
	
	private void initFreeMarkerCongation(){
		configuration.setClassForTemplateLoading(this.getClass(), "/template");
	}
	
	protected abstract String projectPath();
	
	protected abstract String templateName();
	
	protected abstract File processFile();
	
	@Override
	public void genCode() {
		this.init();
		this.codeFile = this.processFile();
		Map<String, Object> modelAttribute = this.initModelAttribute();
		this.processTemplateToFile(modelAttribute);
	}
	
	private Map<String, Object> initModelAttribute(){
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		modelAttributeAutowired.setModelAttribute(model, this);
		addExtractModelAttribute(model);
		return model;
	}
	
	protected void addExtractModelAttribute(Map<String, Object> model){
	}
	
	@SneakyThrows
	private void processTemplateToFile(Map<String, Object> model){
		Template t = configuration.getTemplate(templateName);
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);  
		FileUtils.write(codeFile, content, "utf-8");
		log.info("生成文件content:{}",content);
		log.info("生成文件路径：{}",codeFile.getAbsolutePath());
	}
	
}
