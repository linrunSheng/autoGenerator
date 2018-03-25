package com.lhy.tool.custom;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;
import com.lhy.tool.autoconfigation.GeneratorConfigation;
import com.lhy.tool.generator.Generator;
import com.lhy.tool.model.CrudBean;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@Slf4j
public class CustomWebConfigGenerator implements Generator {

    private static final String WEB_CONFIG_JAVA = "WebConfig.java";
    private static final String SRC_MAIN_RESOURCES = "src/main/resources";
    private static final String TEMP = "temp";

    @SneakyThrows
    @Override
    public File genCode(CrudBean crudBean, GeneratorConfigation generatorConfigation) {
        File file = preResource();
        String content = addViewController(crudBean);
        saveFile(file, content);
        clearTemp();
        return file;
    }

    private void clearTemp() {
        getWebConfigTempFile().delete();
    }

    private void saveFile(File file, String cu) throws IOException {
        if (cu != null) {
            FileUtils.writeStringToFile(file, cu.toString(), Charsets.UTF_8.name());
        }
    }

    private String addViewController(CrudBean crudBean) {
        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(CustomWebConfigGenerator.class).resolve(SRC_MAIN_RESOURCES));
        CompilationUnit cu = sourceRoot.parse(TEMP, WEB_CONFIG_JAVA);
        BlockStmt addViewControllers = cu.findAll(MethodDeclaration.class).stream()
                .filter(methodDeclaration -> methodDeclaration.getName().toString().equals("addViewControllers"))
                .findFirst().get().getBody().get();
        String controllerRequestMapping = crudBean.getModelAttributes().getJavaAttributes().getControllerRequestMapping();
        String viewPath = crudBean.getModelAttributes().getViewAttributes().getViewPath();
        String format = String.format("registry.addViewController(\"/%sview\").setViewName(\"%s\");", controllerRequestMapping, viewPath);
        NodeList<Statement> tempList = new NodeList<>(addViewControllers.getStatements());
        boolean hasSave = tempList.stream().anyMatch(statement -> format.equals(statement.toString()));
        if (!hasSave) {
            addViewControllers.addStatement(format);
            return cu.toString();
        }
        return null;
    }

    private File preResource() throws IOException {
        String oraginPath = "E:\\git-new-res\\crud-generator\\crud-example\\src\\main\\java\\com\\lhy\\config\\" + WEB_CONFIG_JAVA;
        File file = new File(oraginPath);
        File tempFile = getWebConfigTempFile();
        FileUtils.copyFile(file, tempFile);
        return file;
    }

    private File getWebConfigTempFile() {
        String dir = System.getProperty("user.dir");
        if (dir.contains("crud-gen")){
            return new File(dir + "/" + SRC_MAIN_RESOURCES + "/" + TEMP, WEB_CONFIG_JAVA);
        }
        return new File(dir + "/crud-gen/" + SRC_MAIN_RESOURCES + "/" + TEMP, WEB_CONFIG_JAVA);
    }
}