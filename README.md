#  **crud-generator**

# 增删改查代码生成工具  适合后台管理应用的基本增删该查代码的生成

### 工具在springboot的基础上，使用freeMarker 结合持久层tkmapper的generator插件生成代码

生成的代码包含:

jsp（默认 可扩展为html或其他任意视图文件）

js文件

springmvc的 controller文件

service文件

pageModel分页参数模型文件

以及数据库脚本菜单语句 sql文件


### 使用步骤

###  1.确保已经包含数据库表对应的实体对象模型
例如模型类：
```
@Table(name = "T_ZZFW_XQ")
@ApiModel("ZzfwXq（校区对象）")
public class ZzfwXq implements Serializable {
    /**
     * 校区代码
     */
    @Id
    @Column(name = "XQDM")
    @ApiModelProperty(value ="校区代码",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String xqdm;

    /**
     * 备注
     */
    @Column(name = "BZ")
    @ApiModelProperty(value ="备注",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String bz;

    //省略其他字段和Getter Setter方法

    private static final long serialVersionUID = 1L;


    public enum FieldEnum {
        XQDM("xqdm","XQDM"),
		BZ("bz","BZ"),
        //省略其他字段
		;

        private String javaFieldName;

        private String dbFieldName;

        FieldEnum(String javaFieldName, String dbFieldName) {
            this.javaFieldName = javaFieldName;
            this.dbFieldName = dbFieldName;
        }

        public String javaFieldName() {
            return javaFieldName;
        }

        public String dbFieldName() {
            return dbFieldName;
        }
    }
}
```
楼主这里使用扩展的tkmapper-generator插件，模型字段上包含 jpa注解和swagger注解，主要获取表注释和字段注释作为默认的页面标题和字段名称，不是必须，

如果不想使用jpa注解和swagger注解，可以集成抽象类 _AbstractCrudColumnFactory_  和  _AbstractCrudBeanFactory_ 并注册为springbean来自定义模型和列的内容

默认实现： _DefaultCrudColumnFactory_ 和 _DefaultCrudBeanFactory_ 



###  2. 加入模型所在项目的依赖

例如：

```
<dependency>
	<groupId>com.wisedu</groupId>
	<artifactId>zzfw-template</artifactId>
	<version>3.0.1</version>
	<exclusions>
		<exclusion>
			<artifactId>*</artifactId>
			<groupId>*</groupId>
		</exclusion>
	</exclusions>
</dependency>
```



###  3.application.yml配置

```
crudgen:

  project:

    java-project-path: E://workspace//zzfw-pack//zzfw-interface//zzfw-template #生成的工程路径

    view-project-path: E://workspace//zzfw-pack//zzfw-base//zzfw-admin #视图生成的工程路径

  template-path: /templates/default  #模板文件路径  默认为当前项目 resources/templates/default下

  controller-enabled: true #是否生成controller

  service-enabled: true #是否生成service服务

  page-model-param-enabled: true #是否生成分页模型参数

  view-enabled: true #是否生成视图 默认jsp

  js-enabled: true #是否生成js

  sql-enabled: true #是否生成sql

  model-attributes:

    - model-name: ZzfwXq #model名称

      extend-attr-map: #自定义扩展属性

        author: administrator 

      java-attributes:

        model-package: com.wisedu.zzfw.template.campus.model #model包路径

        service-package: com.wisedu.zzfw.template.campus.service #service包路径

        controller-package: com.wisedu.zzfw.campus.web #controller包路径

        controller-request-mapping: manage/campus #controller requestMapping路径

        query-order-sql: px asc,csmc desc,xqmc desc #排序字段,格式为sql中order by 后的内容

      view-attributes:

        view-path: manage/campus #jsp等视图文件相对路径

      column-attr-map:    #列属性

        xqdm:

          index: 1 #顺序号

          nullable: false #是否可空 默认可空

          queryable: true #是否作为查询条件 默认否

          uniqueable: true #是否唯一性校验

          alias: 校区代码 #列别名 页面显示字段名称 为空默认为数据库字段注释

          grid-showable: true #表格是否显示 默认显示

          type: #数据展示类型 text combobox datebox等 暂未实现

          extend-attr-map: #列扩展属性

            custom-attr: 2018

        xqmc:

          index: 2 

          nullable: false

          queryable: true

        csmc:

          index: 0

          nullable: false

          queryable: true

          alias: 城市名称

      menu-attributes:

        module-name: 校区管理 #模块名称

        parent-module-name: 字典表维护 #父模块名称

        role-name: 实施管理员 #角色名称

```

###  4. 运行Application.java 或者mvn spring-boot:run

即可生成 controller service pageModelParam等代码



###  5. 如果默认的配置不满足要求的，可以自定义配置

参考demo

![注册相关配置类以取代默认配置](https://gitee.com/uploads/images/2018/0106/232355_dcd39f94_1009390.png "注册相关配置类以取代默认配置.png")

配置自定义模型参数和列参数

![配置自定义模型参数和列参数](https://gitee.com/uploads/images/2018/0106/232651_8efacc5f_1009390.png "配置自定义模型参数和列参数.png")


###  6 如有不足的地方，欢迎大家指正。