package com.wisedu.zzfw.autoconfigation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.wisedu.zzfw.GeneratorProperties;
import com.wisedu.zzfw.GeneratorPropertiesWarpper;
import com.wisedu.zzfw.generator.AbstractControllerGenerator;
import com.wisedu.zzfw.generator.AbstractPageModelGenerator;
import com.wisedu.zzfw.generator.AbstractServiceGenerator;
import com.wisedu.zzfw.generator.impl.DefaultControllerGenerator;
import com.wisedu.zzfw.generator.impl.DefaultJsGenerator;
import com.wisedu.zzfw.generator.impl.DefaultJspViewGenerator;
import com.wisedu.zzfw.generator.impl.DefaultPageModelGenerator;
import com.wisedu.zzfw.generator.impl.DefaultServiceGenerator;

@Configuration
@EnableConfigurationProperties({GeneratorProperties.class,GeneratorPropertiesWarpper.class})
@Import(CrudFactoryAutoConfigation.class)
public class BuilderAutoConfigation {
	
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
//	
//	@Bean
//	@ConditionalOnMissingBean
//	@ConditionalOnProperty(prefix = "crudgen", name = "sql-enabled",havingValue="true", matchIfMissing = true)
//	public SqlBuilder sqlBuilder(){
//		return new SqlBuilder();
//	}
	
	
}
