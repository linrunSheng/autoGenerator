#  **HY crud-generator**




### 基于springBoot的代码生成工具,适合后台管理应用的基本增删该查代码的生成,简单配置即可搞定一个单页增删改查应用！


### 生产请用2.0.2或master版本，3.0.0版本将切换到springboot2 持久层更换为mybatis-plus，相关代码也会有较大变动，敬请期待。



基础模板后端为springmvc+swagger restful api风格
前端模板为 jquery-easyui风格
支持扩展

### 使用技术

 1. springBoot
 2. springMvc
 3. swagger-ui
 4. freemarker
 5. mybatis-generator
 6. tk通用mapper
 7. thymeleaf
 8. druid
 9. javaparser
 10. 前端框架 easyui vue



### 可生成以下代码:

 - html页面（默认 可扩展为jsp，vue组件,纯html或其他视图文件）
 - js脚本
 - Controller.java后端控制层
 - Service.java 后端服务层
 - Mapper.java 后端dao层
 - mapper.xml 后端dao层xml
 - model.java模型文件

### 模块说明
![模块说明](https://gitee.com/uploads/images/2018/0325/180653_28812020_1009390.png "屏幕截图.png")


### 使用步骤 
#### 以下以生成E:\git-new-res\crud-generator\curd-example 简单springboot应用为例

#####  1.[clone项目到本地][1]

#####  2.连接mysql 数据库创建测试数据库
执行 crud-example\db.sql
即可创建数据库用户以及初始化测试脚本

#####  3. 控制台进入crud-generator项目目录，安装相关依赖

 >   cd e:\git-new-res\crud-generator
 >
 >   mvn clean install

#####  4.切换到curd-example示例项目，通过mybatis generator生成持久层代码

> cd e:\git-new-res\crud-generator\crud-example
>
> mvn mybatis-generator:generate

#####  5.切换到curd-gen项目，生成web层和前端代码  或 main函数执行crud-gen下Application

> cd e:\git-new-res\crud-generator\crud-gen
>
> mvn spring-boot:run

###### 生成的文件目录如下：
后端：

![输入图片说明](https://gitee.com/uploads/images/2018/0325/185156_ccb73c2e_1009390.png "屏幕截图.png")
![输入图片说明](https://gitee.com/uploads/images/2018/0325/185243_e9ad48d9_1009390.png "屏幕截图.png")

前端：

![输入图片说明](https://gitee.com/uploads/images/2018/0325/185312_1795f415_1009390.png "屏幕截图.png")

#####  6. 所有代码生成完毕，启动crud-example项目进行测试 或 main函数执行crud-example下Application

> cd e:\git-new-res\crud-generator\crud-example
>
> mvn spring-boot:run

##### 7.查看后端swagger-ui接口
浏览器输入 [http://127.0.0.1/8080/swagger-ui.html][2]
![接口预览](https://gitee.com/uploads/images/2018/0325/183850_e6871dd8_1009390.png "屏幕截图.png")

##### 8.预览前端页面功能
浏览器输入 [http://127.0.0.1/8080/userview][3]
![前端页面](https://gitee.com/uploads/images/2018/0325/183941_1f3133be_1009390.png "屏幕截图.png")
![编辑](https://gitee.com/uploads/images/2018/0325/184030_17341461_1009390.png "屏幕截图.png")



####  生成器配置 application.yml
crud-gen模块下application.yml
通过简洁的yml语法即可配置生成代码的相关属性
示例如下：

```
crudgen:
  project:
    java-project-path: E:\git-new-res\crud-generator\crud-example #生成的java工程路径
    view-project-path: E:\git-new-res\crud-generator\crud-example #视图生成的工程路径
  template-path: /templates/easyui  #模板路径  默认为当前项目 resources/templates/easyui下
  controller-enabled: true #是否生成controller
  service-enabled: true #是否生成service服务
  view-enabled: true #是否生成视图 默认thymeleaf html
  js-enabled: true #是否生成js
  model-attributes:
    - model-name: User #model名称 支持配置多表 参考yml数组配置
      extend-attr-map: #自定义扩展属性
        author: hyluan
      java-attributes:
        model-package: com.lhy.example.user.model #model包路径
        service-package: com.lhy.example.user.service #service包路径
        controller-package: com.lhy.example.user.web #controller包路径
        controller-request-mapping: user #controller requestMapping路径
        query-order-sql: updated desc #排序字段,格式为sql中order by 后的内容
      view-attributes:
        view-path: example/user #html等视图文件相对路径
      column-attr-map:    #列属性
      #类扩展配置 不是必须，不设置也能正常生成
        id:
          index: 1 #顺序号
          nullable: false #是否可空 默认可空
          queryable: false #是否作为查询条件 默认否
          uniqueable: false #是否唯一性校验 默认否
          alias: 用户ID #列别名 页面显示字段名称 为空默认为数据库字段注释
          grid-showable: false #表格是否显示 默认显示
          type: #数据展示类型 text combobox datebox等 暂未实现
          extend-attr-map: #列扩展属性 map可自定义
            custom-attr: 2018
        name:
          index: 2 #顺序号
          nullable: false #是否可空 默认可空
          queryable: true #是否作为查询条件 默认否
          uniqueable: true #是否唯一性校验
          alias: 用户姓名 #列别名 页面显示字段名称 为空默认为数据库字段注释
          grid-showable: true #表格是否显示 默认显示
        pass:
          grid-showable: false
        phone:
          index: 3
        status:
          index: 4
          nullable: false
          queryable: true
        created:
          index: 5
        updated:
          index: 6
```


#### 配置自定义模型参数和列参数
`crudgen.model-attributes.extend-attr-map`
--配置模型自定义属性
`crudgen.model-attributes. column-attr-map.列名.extend-attr-map`
--#列扩展属性

![配置自定义模型参数和列参数](https://gitee.com/uploads/images/2018/0106/232651_8efacc5f_1009390.png "配置自定义模型参数和列参数.png")

#### 自定义配置
##### 自定义模型和列
如果不想使用jpa注解和swagger注解，可以继承抽象类 _AbstractCrudColumnFactory_  和  _AbstractCrudBeanFactory_ 并注册为spring bean来自定义模型和列的内容

默认实现： _DefaultCrudColumnFactory.java_ 
和           _DefaultCrudBeanFactory.java_ 

##### 增量WebMvcConfigurerAdapter配置
如果前后端未分离需要设置controller和view对应关系
传统方式是采用@Controller注解映射
例如：
![输入图片说明](https://gitee.com/uploads/images/2018/0325/190955_a616b290_1009390.png "屏幕截图.png")
本项目服务端全部使用@RestController接口，不映射视图
将映射模块作为一个扩展功能，根据模型增量生成视图和view映射关系

![输入图片说明](https://gitee.com/uploads/images/2018/0325/191111_b42c3188_1009390.png "屏幕截图.png")

参考curd-gen模块下：
```
CustomWebConfigGenerator.java
```

##### 自定义模板文件生成器
继承对应的abstractXXGenerator
![输入图片说明](https://gitee.com/uploads/images/2018/0325/191423_27effcb3_1009390.png "屏幕截图.png")

并添加@Component注解即可替换默认的模板文件生成器

默认的后端生成器为springmvc+mybatis+mapper方式
默认的前端为easyui html方式




###### 更多自定义内容请参考：
`crud-generator\crud-gen\src\main\java\com\lhy\tool\custom`目录
![注册相关配置类以取代默认配置](https://gitee.com/uploads/images/2018/0106/232355_dcd39f94_1009390.png "注册相关配置类以取代默认配置.png")


###### 单个引用
以下两个模块可单独引入

 **crud-mvc** 

为基于springmvc封装的一个通用controller和service接口，基于restful-api和泛型设计
继承BaseControllerImpl即可包含常用增删改查接口功能
![输入图片说明](https://gitee.com/uploads/images/2018/0325/200114_9891cef1_1009390.png "屏幕截图.png")

 **crud-plugin** 

mybatis-geneator 扩展插件， 包含以下扩展功能   
```
<!--开启swagger-ui注解-->
<property name="swaggerApiEnabled" value="true" />
<!--实现序列化接口 默认true -->
<property name="implementSerializableInteface" value="true" />  
<!--实体类增加字段名称枚举 默认true -->
<property name="modelFieldEnum" value="true" /> 
<!--setter方法链式调用（返回this） 默认true -->
<property name="setterMethodChainEnabled" value="true" />
```

#### 愿景
1. 可视化页面配置
2. vue模板实现
3. 步骤整合，简化流程
4. controller 暴露接口可选择性配置


####  这个工具功能并不是很完善的，主要是说明下设计过程中使用到的一些较为适用的设计思想和模式

例如：封装变化，单一职责，多组合少继承，对修改关闭对扩展开放等等。。。

欢迎大家交流学习。


  [1]: https://gitee.com/luanhaoyu/crud-generator.git
  [2]: http://127.0.0.1/8080/swagger-ui.html
  [3]: http://127.0.0.1/8080/userview