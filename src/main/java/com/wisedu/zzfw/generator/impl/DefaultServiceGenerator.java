package com.wisedu.zzfw.generator.impl;

import java.io.File;

import com.wisedu.zzfw.generator.AbstractServiceGenerator;
import com.wisedu.zzfw.model.CrudBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultServiceGenerator extends AbstractServiceGenerator {

	@Override
	protected String queryOrderSql(CrudBean crudBean) {
		return crudBean.getModelAttributes().getJavaAttributes().getQueryOrderSql();
	}

}

