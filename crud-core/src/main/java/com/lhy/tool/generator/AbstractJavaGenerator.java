package com.lhy.tool.generator;

import com.lhy.tool.autoconfigation.GeneratorProperties.ModelAttributes.JavaAttributes;
import com.lhy.tool.model.Clazz;
import com.lhy.tool.model.CrudBean;
import com.lhy.tool.model.JavaAttribute;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public abstract class AbstractJavaGenerator extends AbstractGenerator {

    JavaAttribute javaAttribute;

    @Override
    protected void initGeneratorConfig(CrudBean crudBean) {
        super.initGeneratorConfig(crudBean);
        this.javaAttribute = javaAttribute(crudBean);
    }

    private JavaAttribute javaAttribute(CrudBean crudBean) {
        JavaAttributes javaAttributes = crudBean.getModelAttributes().getJavaAttributes();
        String modelFullName = crudBean.getFullName();
        String modelSimpleName = crudBean.getSimpleName();
        String primaryKeyType = crudBean.getColumns().stream().filter(crudColumn -> crudColumn.getPrimary()).findFirst().get().getColumnType();

        return JavaAttribute.builder().description(crudBean.getDescription())
                .model(new Clazz(modelFullName)).pageModel(new Clazz(modelFullName + "Param"))
                .service(new Clazz(javaAttributes.getServicePackage() + "." + modelSimpleName + "Service"))
                .controller(
                        new Clazz(javaAttributes.getControllerPackage() + "." + modelSimpleName + "Controller"))
                .primaryKeyType(primaryKeyType)
                .build();
    }

    /**
     * @return
     * @Title: curClzz
     * @Description: 当前需要生成的类
     */
    protected abstract Clazz curClazz(JavaAttribute javaAttribute);

    protected String queryOrderSql(CrudBean crudBean) {
        return crudBean.getModelAttributes().getJavaAttributes().getQueryOrderSql();
    }

    /**
     * 生成的代码文件
     */
    protected File getCodeFile() {
        Clazz curClazz = this.curClazz(this.javaAttribute);
        String packageName = curClazz.getPackageName();
        String filePath = this.fileAttribute.getProjectPath() + "/src/main/java/" + packageName.replaceAll("\\.", "/")
                + "/" + curClazz.getName() + ".java";
        return new File(filePath);
    }

}
