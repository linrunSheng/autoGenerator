package ${javaAttribute.service.packageName};

import com.lhy.commonweb.service.AbstractService;
import ${javaAttribute.model.fullName};
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * ${javaAttribute.service.name}
 * <p/>
 *  ${javaAttribute.description}
 * @author hyluan
 * @date 2018/7/18 18:37
 * Copyright (c) 2018 wisedu
 */
@Service
public class ${javaAttribute.service.name} extends AbstractService<${javaAttribute.model.name}, ${javaAttribute.primaryKeyType}>{

	public ${javaAttribute.service.name}(@Qualifier("${javaAttribute.entity.parametterName}Mapper") Mapper<${javaAttribute.model.name}> mapper) {
		super(mapper);
	}

}
