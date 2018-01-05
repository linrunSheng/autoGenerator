package com.wisedu.zzfw.custom;

import java.util.Map;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes;
import com.wisedu.zzfw.model.CrudBean;
import com.wisedu.zzfw.model.factory.AbstractCrudBeanFactory;

public class CustomCrudBeanFactory extends AbstractCrudBeanFactory {

	
	@Override
	protected Class<? extends CrudBean> crudBeanClass() {
		return CustomCrudBean.class;
	}

	@Override
	protected void initBeanCustomProperties(CrudBean bean,ModelAttributes modelAttributes) {
		CustomCrudBean newInstance = (CustomCrudBean)bean;
		Map<String, Object> extendAttrMap = modelAttributes.getExtendAttrMap();
		Object customAttr1 = extendAttrMap.get("customAttr1");
		newInstance.setCustomAtrr1(String.valueOf(customAttr1));
	}

}
