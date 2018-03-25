#  **crud-generator**

# 增删改查代码生成工具  适合后台管理应用的基本增删该查代码的生成
基础模板后端为springmvc+swagger restful api风格
前端模板为 jquery-easyui风格

### 工具在springboot的基础上，使用freeMarker,javaparser 结合持久层mybatis generator插件生成代码

生成的代码包含:

html（默认 可扩展为jsp，vue,html或其他任意视图文件）
js
controller
service
mapper.java
mapper.xml
model.java模型文件
### 使用步骤 以下以生成E:\git-new-res\crud-generator\curd-example 简单应用为例

###  1.clone项目到本地

###  2.连接mysql 数据库创建测试数据库
执行crud-example\db.sql

###  3. 控制台进入crud-generator项目目录，安装相关依赖
cd e:\git-new-res\crud-generator\
mvn clean install

###  4.切换到curd-example示例项目，通过mybatis generator生成dao层代码
cd e:\git-new-res\crud-generator\crud-example
mvn mybatis-generator:generate

###  5.切换到curd-gen实例项目，生成web和前端代码  或 main函数允许crud-gen下Application
cd e:\git-new-res\crud-generator\crud-gen
mvn spring-boot:run

###  6. 所有代码生成完毕，启动crud-example项目进行测试 或 main函数允许crud-example下Application
cd e:\git-new-res\crud-generator\crud-example
mvn spring-boot:run

## 查看后端swagger-ui接口
浏览器输入 http://127.0.0.1/8080/swagger-ui.html

## 预览前端页面功能
浏览器输入 http://127.0.0.1/8080/userview




楼主这里使用扩展的tkmapper-generator插件，模型字段上包含 jpa注解和swagger注解，主要获取表注释和字段注释作为默认的页面标题和字段名称，

如果不想使用jpa注解和swagger注解，可以集成抽象类 _AbstractCrudColumnFactory_  和  _AbstractCrudBeanFactory_ 并注册为springbean来自定义模型和列的内容

默认实现： _DefaultCrudColumnFactory_ 和 _DefaultCrudBeanFactory_ 



###  

例如：




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




###  5. 如果默认的配置不满足要求的，可以自定义配置







r
参考demo

![注册相关配置类以取代默认配置](https://gitee.com/uploads/images/2018/0106/232355_dcd39f94_1009390.png "注册相关配置类以取代默认配置.png")

配置自定义模型参数和列参数

![配置自定义模型参数和列参数](https://gitee.com/uploads/images/2018/0106/232651_8efacc5f_1009390.png "配置自定义模型参数和列参数.png")


###  6  这个工具功能不是最强大的最完善的，主要是展示下鄙人设计过程中使用到的一些较为适用的设计思想和模式

例如：封装变化，单一职责，多组合少继承，对修改关闭对扩展开放等等。。。如有不足欢迎指正。