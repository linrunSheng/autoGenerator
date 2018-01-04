package com.wisedu.zzfw;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import com.wisedu.zzfw.generator.Generator;
import com.wisedu.zzfw.viewmodel.CrudBean;

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
	GeneratorPropertiesWarpper generatorProperties;
	
	@Autowired
	GeneratorConfigation generatorConfigation;
	
	@PostConstruct
	private void init() throws IOException{
		log.info("开始生成");
		if (CollectionUtils.isEmpty(generators)) {
			throw new NullPointerException("至少需要一个代码生成器Generator");
		}
		List<CrudBean> beanModelClasses = generatorProperties.getCrudBeanList();
		for (CrudBean beanModel : beanModelClasses) {
			for (Generator generator : generators) {
				generator.genCode(beanModel, generatorConfigation);
			}
		}
		log.info("生成结束");
		System.exit(0);
	}
}
