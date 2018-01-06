package com.lhy.tool.custom;

import org.springframework.stereotype.Component;

import com.lhy.tool.generator.AbstractControllerGenerator;
import com.lhy.tool.model.CrudBean;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class CustomControllerGenerator extends AbstractControllerGenerator {
	
	String author;
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected void writeAttribute(CrudBean crudBean) {
		CustomCrudBean bean = (CustomCrudBean)crudBean;
		this.author = bean.getAuthor();
	}
	
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected String templateName() {
		return "TemplateController-custom.java";
	}
	

}

