package com.lhy.tool.custom;

import java.util.Comparator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes;
import com.lhy.tool.model.CrudBean;
import com.lhy.tool.model.CrudColumn;
import com.lhy.tool.model.factory.AbstractCrudBeanFactory;
import com.lhy.tool.model.factory.CrudColumnFactory;

@Component
public class CustomCrudBeanFactory extends AbstractCrudBeanFactory {
	
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Autowired
	@Override
	public AbstractCrudBeanFactory setCrudColumnFactory(CrudColumnFactory crudColumnFactory) {
		this.crudColumnFactory = crudColumnFactory;
		return this;
	}
	
	@Override
	protected Class<? extends CrudBean> crudBeanClass() {
		return CustomCrudBean.class;
	}

	@Override
	protected void initBeanCustomProperties(CrudBean bean,ModelAttributes modelAttributes) {
		CustomCrudBean newInstance = (CustomCrudBean)bean;
		Map<String, Object> extendAttrMap = modelAttributes.getExtendAttrMap();
		Object author = extendAttrMap.get("author");
		newInstance.setAuthor(String.valueOf(author));
	}

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected Comparator<CrudColumn> columnsComparator() {
		return null;
	}
	
	

}
