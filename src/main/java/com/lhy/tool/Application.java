package com.lhy.tool;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import com.lhy.tool.autoconfigation.GeneratorConfigation;
import com.lhy.tool.generator.Generator;
import com.lhy.tool.model.CrudBean;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired(required=false)
	List<Generator> generators;
	
	@Autowired
	GeneratorConfigation generatorConfigation;
	
	@PostConstruct
	private void init() throws IOException{
		log.info("增删改查代码开始生成");
		checkGenerator();
		List<CrudBean> crudBeans = generatorConfigation.getGeneratorProperties().getCrudBeanList();
		checkCrudBeans(crudBeans);
		for (CrudBean crudBean : crudBeans) {
			for (Generator generator : generators) {
				generator.genCode(crudBean, generatorConfigation);
			}
		}
		log.info("生成结束");
		System.exit(0);
	}
	
	private void checkGenerator(){
		if (CollectionUtils.isEmpty(generators)) {
			throw new NullPointerException("至少需要一个代码生成器Generator");
		}
		log.info("Generator数量：{}",generators.size());
		for (Generator generator : generators) {
			log.info("generator: {}",generator.getClass().getSimpleName());
		}
	}
	
	private void checkCrudBeans(List<CrudBean> crudBeans){
		if (CollectionUtils.isEmpty(crudBeans)) {
			throw new NullPointerException("至少需要一个crudBeans模型");
		}
		log.info("crudBean数量：{}",crudBeans.size());
		for (CrudBean crudBean : crudBeans) {
			log.info("crudBean: {}",crudBean.toString());
		}
	}
}
