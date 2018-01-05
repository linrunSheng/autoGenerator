package com.wisedu.zzfw.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.wisedu.zzfw.GeneratorConfigation;
import com.wisedu.zzfw.GeneratorProperties.Project;
import com.wisedu.zzfw.generator.annotation.Model;
import com.wisedu.zzfw.generator.annotation.ModelIgnoreAttribute;
import com.wisedu.zzfw.model.CrudBean;
import com.wisedu.zzfw.model.CrudColumn;
import com.wisedu.zzfw.model.FileAttribute;

import freemarker.template.Template;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
@Model
public abstract class AbstractGenerator implements Generator {
	
	@ModelIgnoreAttribute
	protected FileAttribute fileAttribute;
	
	protected List<CrudColumn> columns;
	
	protected void init(CrudBean crudBean){
		columns = crudBean.getColumns();
	}
	
	protected abstract String projectPath(Project project);
	
	/**
	 * 
	* @Title: templateName
	* @Description: 使用的模板名称  
	* @return String    返回类型
	* @return
	 */
	protected abstract String templateName();
	
	/**
	 * 
	* @Title: getFile
	* @Description:生成的代码文件
	* @return File    返回类型
	* @return
	 */
	protected abstract File getFile();
	
	@Override
	public final File genCode(CrudBean beanModel, GeneratorConfigation generatorConfigation) {
		this.initFileAttribute(beanModel, generatorConfigation);
		this.init(beanModel);
		File codeFile = this.getFile();
		this.fileAttribute.setCodeFile(codeFile);
		Map<String, Object> modelAttribute = this.initModelAttribute(generatorConfigation);
		this.processTemplateToFile(generatorConfigation,modelAttribute);
		this.checkFile(codeFile);
		return codeFile;
	}
	
	private void initFileAttribute(CrudBean beanModel, GeneratorConfigation generatorConfigation){
		this.fileAttribute = FileAttribute.builder().projectPath(projectPath(generatorConfigation.getProject()))
				.templateName(templateName()).build();
	}
	
	
	private Map<String, Object> initModelAttribute(GeneratorConfigation generatorConfigation){
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		generatorConfigation.getModelAttributeAutowired().setModelAttribute(model, this);
		addExtractModelAttribute(model);
		return model;
	}
	
	protected void addExtractModelAttribute(Map<String, Object> model){
	}
	
	@SneakyThrows
	private void processTemplateToFile(GeneratorConfigation generatorConfigation, Map<String, Object> model){
		Set<Entry<String,Object>> entrySet = model.entrySet();
		log.debug("模板参数");
		for (Entry<String, Object> entry : entrySet) {
			log.debug("key:{},value:{}",entry.getKey(),entry.getValue());
		}
		Template t = generatorConfigation.getConfiguration().getTemplate(fileAttribute.getTemplateName());
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);  
		File codeFile = fileAttribute.getCodeFile();
		FileUtils.write(codeFile, content, "utf-8");
		log.info("生成文件content:{}",content);
		log.info("生成文件路径：{}",codeFile.getAbsolutePath());
	}
	
	@SneakyThrows
	private void checkFile(File file){
		if (!file.exists()) {
			throw new FileNotFoundException("生成文件失败");
		}
		if (file.length() == 0) {
			throw new FileSystemException(file.getAbsolutePath(),"生成文件长度为0",null);
		}
	}
	
}
