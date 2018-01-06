###  ** **crud-generator** ** 

增删改查代码生成工具

### 使用freeMarker 结合tkMybatis生成代码 从前端jsp,js,到后端controller，service，pageModel


### ### 使用步骤


### 2.application.yml配置

crudgen:
  project:
    java-project-path: E://workspace//zzfw-pack//zzfw-interface//zzfw-template #生成的工程路径
    view-project-path: E://workspace//zzfw-pack//zzfw-base//zzfw-admin #视图生成的工程路径
  template-path: /templates/default  #模板路径  默认为当前项目 resources/templates/default下
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
        lxdh:
          index: 3
          grid-showable: false
          nullable: false
        xqdz:
          index: 4
          grid-showable: false
          nullable: false
        sfsy:
          index: 5
          alias: 是否启用
          nullable: false
        lng:
          nullable: false
          index: 6
        lag:
          nullable: false
          index: 7  
      menu-attributes:
        module-name: 校区管理 #模块名称
        parent-module-name: 字典表维护 #父模块名称
        role-name: 实施管理员 #角色名称


### 3.运行Application.java
即可生成 controller service pageModelParam等代码



### 待实现：
1.所有代码表集合到一个查询页面 todo

2.表格展示字段可以控制是否显示 ok

3.查询条件每行三个，超过三个换行 todo

4.字段名称默认从表中获取注释，可以对字段名称起别名， ok

5.字段展示顺序排序 ok

5.可自定义模板生成器，开发者自己决定采用什么模板生成器，默认使用自定义模板生成器 ok

6.优化generator 成员变量 ok

7.结合mbg 一键生成 todo


