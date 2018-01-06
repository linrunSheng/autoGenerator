package com.wisedu.zzfw.custom;

import org.springframework.stereotype.Component;

import com.wisedu.zzfw.generator.AbstractControllerGenerator;
import com.wisedu.zzfw.model.CrudBean;

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

