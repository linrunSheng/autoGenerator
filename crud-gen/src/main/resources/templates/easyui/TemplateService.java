package ${javaAttribute.service.packageName};

import com.lhy.commonweb.service.AbstractService;
import ${javaAttribute.model.fullName};
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;


/**
 * 
* @ClassName: ${javaAttribute.service.name}
* @Description: ${javaAttribute.description}
* @author
* @date 2017年12月18日 下午5:08:53
* @Copyright: Copyright (c) 2017 wisedu
 */
@Service
public class ${javaAttribute.service.name} extends AbstractService<${javaAttribute.model.name}, ${javaAttribute.primaryKeyType}>{

	public ${javaAttribute.service.name}(@Qualifier("${javaAttribute.model.parametterName}Mapper") Mapper<${javaAttribute.model.name}> mapper) {
		super(mapper);
	}




}
