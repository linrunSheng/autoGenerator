package com.wisedu.zzfw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "crudgen")
public class GeneratorProperties {

	private Project project;
	
	private String templatePath = "/template";
	
	/**
	 * 是否启用
	 */
	private boolean controllerEnabled = true;
	private boolean JsEnabled = true;
	private boolean jspEnabled = true;
	private boolean pageModelParamEnabled = true;
	private boolean serviceEnabled = true;
	private boolean sqlEnabled = true;

	private List<ModelAttributes> modelAttributes = new ArrayList<ModelAttributes>();
	
	@Setter
	@Getter
	public static class Project{
		private String javaProjectPath;

		private String viewProjectPath;
	}
	
	
	@Setter
	@Getter
	public static class ModelAttributes {

		private String modelName;

		private JavaAttributes javaAttributes = new JavaAttributes();

		private ViewAttributes viewAttributes = new ViewAttributes();

		/**
		 * 菜单
		 */
		private MenuAttributes menuAttributes = new MenuAttributes();
		
		private Map<String,ColumnAttributes> columnAttrMap = new HashMap<String,ColumnAttributes>();

		@Setter
		@Getter
		public static class JavaAttributes {

			private String modelPackage;

			private String servicePackage;

			private String controllerPackage;

			private String controllerRequestMapping;
			
			private String queryOrderSql;

		}

		@Setter
		@Getter
		public static class ViewAttributes {

			private String viewPath;
		}
		
		@Setter
		@Getter
		public static class ColumnAttributes {
			
			/**
			 * 别名 即页面显示字段名称
			 */
			private String alias;
			
			/**
			 * 是否是查询条件
			 */
			private boolean queryable;
			
			/**
			 * 索引 即排序号
			 */
			private int index;
			
			/**
			 * 是否可为空
			 */
			private boolean nullable = true;
			
			/**
			 * 是否唯一
			 */
			private boolean uniqueable;
			
			/**
			 * 表格是否显示
			 */
			private boolean gridShowable = true;
			
			/**
			 * 数据类型
			 */
			private String type;
			
		}

		@Setter
		@Getter
		public static class MenuAttributes {

			/**
			 * 二级模块名称
			 */
			private String moduleName;

			/**
			 * 需要生成菜单的角色名称
			 */
			private String roleName = "实施管理员";

			/**
			 * 父模块名称
			 */
			private String parentModuleName;

		}
	}
}
