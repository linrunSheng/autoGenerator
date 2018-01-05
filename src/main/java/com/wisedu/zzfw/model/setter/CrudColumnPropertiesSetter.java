package com.wisedu.zzfw.model.setter;

import java.lang.reflect.Field;

import org.springframework.core.Ordered;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.wisedu.zzfw.model.CrudColumn;

/**
 * @ClassName: ColumnAttributeSetter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author luanhy
 * @date 2018年1月4日 下午10:09:51
 * @Copyright: Copyright (c) 2017 wisedu
 */
public interface CrudColumnPropertiesSetter extends Ordered{

	void setColumnFieldValue(CrudColumn columnAttribute, Field field,
			ColumnAttributes configColumnAttributes);
}
