package com.lhy.tool.generator;

import com.lhy.tool.autoconfigation.GeneratorConfigation;
import com.lhy.tool.model.CrudBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Slf4j
public class GeneratorHelper {

    @Autowired(required = false)
    List<Generator> generators;

    @Autowired
    GeneratorConfigation generatorConfigation;

    @PostConstruct
    public void generator() {
        log.info("增删改查代码开始生成");
        checkGenerator();
        List<CrudBean> crudBeans = generatorConfigation.getGeneratorProperties().getCrudBeanList();
        checkCrudBeans(crudBeans);

        //1.8
        crudBeans.forEach(crudBean -> {
            generators.forEach(generator -> generator.genCode(crudBean, generatorConfigation));
        });

        log.info("生成结束");
        System.exit(0);
    }

    private void checkGenerator() {
        if (CollectionUtils.isEmpty(generators)) {
            throw new NullPointerException("至少需要一个代码生成器Generator");
        }
        log.info("Generator数量：{}", generators.size());
        generators.forEach(generator -> log.info("generator: {}", generator.getClass().getSimpleName()));
    }

    private void checkCrudBeans(List<CrudBean> crudBeans) {
        if (CollectionUtils.isEmpty(crudBeans)) {
            throw new NullPointerException("至少需要一个crudBeans模型");
        }
        log.info("crudBean数量：{}", crudBeans.size());
        crudBeans.forEach(crudBean -> log.info("crudBean: {}", crudBean.toString()));
    }
}
