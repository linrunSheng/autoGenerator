package com.lhy.tool.model.binding;

import java.lang.reflect.Field;

import org.springframework.core.Ordered;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.lhy.tool.model.CrudColumn;

/**
 * @ClassName: CrudColumnPropertiesBinding
 * @Description: 设置crudlColumn属性
 * @author luanhy
 * @date 2018年1月4日 下午10:09:51
 * @Copyright: Copyright (c) 2017 wisedu
 */
public interface CrudColumnPropertiesBinding extends Ordered{

	void binding(CrudColumn columnAttribute, Field field,
			ColumnAttributes configColumnAttributes);
}
