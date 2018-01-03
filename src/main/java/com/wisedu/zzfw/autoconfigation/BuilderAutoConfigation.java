package com.wisedu.zzfw.autoconfigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wisedu.zzfw.GeneratorProperties;
import com.wisedu.zzfw.GeneratorPropertiesWarpper;
import com.wisedu.zzfw.generator.impl.AbstractControllerGenerator;
import com.wisedu.zzfw.generator.impl.DefaultControllerGenerator;

@Configuration
@EnableConfigurationProperties({GeneratorProperties.class,GeneratorPropertiesWarpper.class})
public class BuilderAutoConfigation {
	
	@Autowired
	GeneratorPropertiesWarpper generatorPropertiesWarpper;
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "crudgen", name = "controller-enabled",havingValue="true", matchIfMissing = true)
	public AbstractControllerGenerator controllerBuilder(){
		return new DefaultControllerGenerator();
	}
	
//	@Bean
//	@ConditionalOnMissingBean
//	@ConditionalOnProperty(prefix = "crudgen", name = "service-enabled",havingValue="true", matchIfMissing = true)
//	public ServiceBuilder serviceBuilder(){
//		return new ServiceBuilder();
//	}
//	
//	@Bean
//	@ConditionalOnMissingBean
//	@ConditionalOnProperty(prefix = "crudgen", name = "page-model-param-enabled",havingValue="true", matchIfMissing = true)
//	public PageModelParamBuilder pageModelParamBuilder(){
//		return new PageModelParamBuilder();
//	}
//	
//	@Bean
//	@ConditionalOnMissingBean
//	@ConditionalOnProperty(prefix = "crudgen", name = "jsp-enabled",havingValue="true", matchIfMissing = true)
//	public JspBulider jspBulider(){
//		return new JspBulider();
//	}
//
//	@Bean
//	@ConditionalOnMissingBean
//	@ConditionalOnProperty(prefix = "crudgen", name = "js-enabled",havingValue="true", matchIfMissing = true)
//	public JsBulider jsBulider(){
//		return new JsBulider();
//	}
//	
//	@Bean
//	@ConditionalOnMissingBean
//	@ConditionalOnProperty(prefix = "crudgen", name = "sql-enabled",havingValue="true", matchIfMissing = true)
//	public SqlBuilder sqlBuilder(){
//		return new SqlBuilder();
//	}
	
	
}
