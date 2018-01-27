package com.lhy.tool.model.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes;
import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.lhy.tool.model.CrudBean;
import com.lhy.tool.model.CrudColumn;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractCrudBeanFactory implements CrudBeanFactory {

	@Getter
	@Setter
	protected CrudColumnFactory crudColumnFactory;

	@Override
	public CrudBean newInstance(ModelAttributes modelAttributes) {
		return newInstance(genBeanClass(modelAttributes), modelAttributes);
	}

	@SneakyThrows
	protected CrudBean newInstance(Class beanClass, ModelAttributes modelAttributes) {
		CrudBean crudBean = crudBeanClass().newInstance();
		this.initBeanProperties(crudBean, beanClass, modelAttributes);
		this.initBeanCustomProperties(crudBean, modelAttributes);
		return crudBean;
	}

	protected abstract Class<? extends CrudBean> crudBeanClass();

	protected abstract void initBeanCustomProperties(CrudBean bean, ModelAttributes modelAttributes);

	@SuppressWarnings("rawtypes")
	@SneakyThrows
	private Class genBeanClass(ModelAttributes modelAttributes) {
		String className = modelAttributes.getJavaAttributes().getModelPackage() + "." + modelAttributes.getModelName();
		return Class.forName(className);
	}

	private void initBeanProperties(CrudBean crudBean, Class beanClass, ModelAttributes modelAttributes) {
		if (crudColumnFactory == null) {
			throw new NullPointerException("crudColumnFactory为空，建议通过@Bean方式注入一个具体实现工厂，或者通过覆盖setCrudColumnFactory方法注入");
		}
		crudBean.setSimpleName(beanClass.getSimpleName()).setFullName(beanClass.getName())
				.setModelAttributes(modelAttributes);
		ApiModel annotation = (ApiModel) beanClass.getAnnotation(ApiModel.class);
		if (annotation != null) {
			String value = annotation.value();
			crudBean.setDescription(value);
		}


		Map<String, ColumnAttributes> configColumns = modelAttributes.getColumnAttrMap();
		Field[] declaredFields = beanClass.getDeclaredFields();
//		List<CrudColumn> columns = new ArrayList<CrudColumn>();
//		for (Field field : declaredFields) {
//			if (!Modifier.isStatic(field.getModifiers())) { // 判断是否静态属性
//				CrudColumn newInstance = crudColumnFactory.newInstance(field, configColumns.get(field.getName()));
//				columns.add(newInstance);
//			}
//		}
//		columns = this.sortColumns(columns);
		//1.8
		List<CrudColumn> columns = Stream.of(declaredFields)
				.filter(field -> !Modifier.isStatic(field.getModifiers()))
				.map(field -> crudColumnFactory.newInstance(field, configColumns.get(field.getName())))
				.sorted(getCrudColumnComparator())
				.collect(Collectors.toList());
		crudBean.setColumns(columns);
	}

	/**
	 * @param columns
	 * @return List
	 * @Title: sortColumns
	 * @Description: 排序 index升序排列
	 */
	private List<CrudColumn> sortColumns(List<CrudColumn> columns) {
		Comparator<CrudColumn> columnsComparator = getCrudColumnComparator();
		Collections.sort(columns, columnsComparator);
		return columns;
	}

	private Comparator<CrudColumn> getCrudColumnComparator() {
		Comparator<CrudColumn> columnsComparator = null;
		if (this.columnsComparator() == null) {
			columnsComparator = new DefaultColumnsComparator();
		}
		return columnsComparator;
	}

	/**
	 * @return Comparator<CrudColumn>
	 * @Title: columnsComparator
	 * @Description: 列排序比较器 如果为空采用默认列比较器
	 */
	protected abstract Comparator<CrudColumn> columnsComparator();

	static class DefaultColumnsComparator implements Comparator<CrudColumn> {

		public int compare(CrudColumn o1, CrudColumn o2) {
			return o2.getColumnAttributes().getIndex() - o1.getColumnAttributes().getIndex();
		}
	}

}
