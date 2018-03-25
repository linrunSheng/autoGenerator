package com.lhy.tool.generator.impl;

import com.lhy.tool.generator.AbstractControllerGenerator;
import com.lhy.tool.model.CrudBean;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultControllerGenerator extends AbstractControllerGenerator {

	String author;
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected void writeAttribute(CrudBean crudBean) {
		this.author = crudBean.getAuthor();
	}

}

