package com.wisedu.zzfw;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
	
	@Getter
	@Setter
	public static class Project{
		private String javaProjectPath;

		private String viewProjectPath;
	}
	
	
	@Getter
	@Setter
	public static class ModelAttributes {

		private String modelName;

		private JavaAttributes javaAttributes = new JavaAttributes();

		private ViewAttributes viewAttributes = new ViewAttributes();

		/**
		 * 菜单
		 */
		private MenuAttributes menuAttributes = new MenuAttributes();

		@Getter
		@Setter
		public static class JavaAttributes {

			private String modelPackage;

			private String servicePackage;

			private String controllerPackage;

			private String controllerRequestMapping;

		}

		@Getter
		@Setter
		public static class ViewAttributes {

			private String viewPath;

			private String queryColumns;

			private String queryOrderSql;

			private String requiredColumns;

			private String validExistsColumns;
		}

		@Getter
		@Setter
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
