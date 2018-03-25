package com.lhy.tool.generator.binding;

import com.lhy.tool.autoconfigation.GeneratorConfigation;
import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes;
import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.lhy.tool.generator.AbstractGenerator;
import com.lhy.tool.model.ExtendAttribute;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
* @ClassName: GeneratorModelAttributeBinding
* @Description: 生成器模型属性绑定
* @author  luanhy
* @date 2018年1月6日 下午3:12:37
* @Copyright: Copyright (c) 2017 wisedu
*/
@Component
public class GeneratorModelAttributeBinding {
	
	@Autowired
	ModelAttributeBinding modelAttributeBinding;
	/**
	 * 
	* @Title: bingdingGeneratorModelAttribute
	* @Description: 绑定AbstractGenerator的模型属性到freemarker视图
	* @param generatorConfigation
	* @return
	 */
	public Map<String, Object> bingdingGeneratorModelAttribute(AbstractGenerator generator, GeneratorConfigation generatorConfigation){
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		ExtendAttribute extendAttribute = this.prepareExtractModelParams(generator.getModelName(), generatorConfigation);
		generator.addExtractModelAttribute(generator.getModelName(), extendAttribute, model);
		this.bindingObjectFieldToModel(model, generator);
		return model;
	}
	
	private void bindingObjectFieldToModel(Map<String,Object> model, Object object){
		modelAttributeBinding.binding(model, object);
	}
	
	private ExtendAttribute prepareExtractModelParams(String modelName,GeneratorConfigation generatorConfigation){
		ExtendAttribute extendAttribute = new ExtendAttribute();
		List<ModelAttributes> modelAttributes = generatorConfigation.getGeneratorProperties().getModelAttributes();
		for (ModelAttributes modelAttributes1 : modelAttributes) {
			if (modelAttributes1.getModelName().equals(modelName)) {
				Map<String, Object> beanExtendAttrMap = modelAttributes1.getExtendAttrMap();
				if (MapUtils.isEmpty(beanExtendAttrMap)) {
					return extendAttribute;
				}
				extendAttribute.setBeanExtendAttrMap(beanExtendAttrMap);
				
				Map<String, ColumnAttributes> columnAttrMap = modelAttributes1.getColumnAttrMap();
				if (MapUtils.isNotEmpty(columnAttrMap)) {
					this.prepareExtractColumnParams(extendAttribute, columnAttrMap);
				}
			}
		}
		return extendAttribute;
	}
	
	private void prepareExtractColumnParams(ExtendAttribute extendAttribute,Map<String, ColumnAttributes> columnAttrMap){
		Set<Entry<String, ColumnAttributes>> entrySet = columnAttrMap.entrySet();
		for (Entry<String, ColumnAttributes> entry : entrySet) {
			Map<String, Object> extendAttrMap = entry.getValue().getExtendAttrMap();
			if (MapUtils.isNotEmpty(columnAttrMap)) {
				extendAttribute.getColumnExtendAttrMap().put(entry.getKey(), extendAttrMap);
			}
		}
	}
}
