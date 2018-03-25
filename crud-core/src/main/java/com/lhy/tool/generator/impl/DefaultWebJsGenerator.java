package com.lhy.tool.generator.impl;

import com.lhy.tool.generator.AbstractViewGenerator;
import com.lhy.tool.model.CrudBean;
import com.lhy.tool.model.CrudColumn;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 非springbootjs生成器
 */
@Getter
@Setter
public class DefaultWebJsGenerator extends AbstractViewGenerator {

    String ctx = "${ctx}";

    String uniqueColumns;

    @Override
    protected String templateName() {
        return "template.js";
    }

    /**
     * {@inheritDoc}
     *
     * @Description:
     */
    @Override
    protected void writeAttribute(CrudBean crudBean) {
        this.uniqueColumns = uniqueColumns();
    }

    private String uniqueColumns() {
        List<CrudColumn> columns = this.getColumns();
        return "[" + columns.stream()
                .filter(crudColumn -> crudColumn.getColumnAttributes().getUniqueable())
                .map(crudColumn -> "'" + crudColumn.getColumnName() + "'")
                .collect(Collectors.joining(",")) + "]";
    }

    @Override
    protected File getCodeFile() {
        String projectPath = this.getFileAttribute().getProjectPath();
        String viewPath = this.getControllerAttribute().getViewPath().replaceAll("\\.", "/");
        String filePath = projectPath + "/src/main/webapp/resources/js/" + viewPath + ".js";
        return new File(filePath);
    }

}
