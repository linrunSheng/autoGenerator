package com.lhy.tool.autoconfigation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.lhy.tool.generator.AbstractControllerGenerator;
import com.lhy.tool.generator.AbstractPageModelGenerator;
import com.lhy.tool.generator.AbstractServiceGenerator;
import com.lhy.tool.generator.impl.DefaultControllerGenerator;
import com.lhy.tool.generator.impl.DefaultJsGenerator;
import com.lhy.tool.generator.impl.DefaultJspViewGenerator;
import com.lhy.tool.generator.impl.DefaultPageModelGenerator;
import com.lhy.tool.generator.impl.DefaultServiceGenerator;
import com.lhy.tool.generator.impl.DefaultSqlGenerator;

@Configuration
@EnableConfigurationProperties({GeneratorProperties.class,GeneratorPropertiesWarpper.class})
@Import({CrudFactoryAutoConfigation.class,GeneratorConfigation.class})
public class GeneratorAutoConfigation {
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "crudgen", name = "controller-enabled",havingValue="true", matchIfMissing = true)
	public AbstractControllerGenerator controllerBuilder(){
		return new DefaultControllerGenerator();
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "crudgen", name = "service-enabled",havingValue="true", matchIfMissing = true)
	public AbstractServiceGenerator serviceGenerator(){
		return new DefaultServiceGenerator();
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "crudgen", name = "page-model-param-enabled",havingValue="true", matchIfMissing = true)
	public AbstractPageModelGenerator pageModelGenerator(){
		return new DefaultPageModelGenerator();
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "crudgen", name = "view-enabled",havingValue="true", matchIfMissing = true)
	public DefaultJspViewGenerator viewGenerator(){
		return new DefaultJspViewGenerator();
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "crudgen", name = "js-enabled",havingValue="true", matchIfMissing = true)
	public DefaultJsGenerator jsGenerator(){
		return new DefaultJsGenerator();
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "crudgen", name = "sql-enabled",havingValue="true", matchIfMissing = true)
	public DefaultSqlGenerator sqlGenerator(){
		return new DefaultSqlGenerator();
	}
	
	
}
