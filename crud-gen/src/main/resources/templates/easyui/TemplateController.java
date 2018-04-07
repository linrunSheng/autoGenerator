package ${javaAttribute.controller.packageName};

import com.lhy.commonweb.model.Page;
import com.lhy.commonweb.model.RequestPage;
import com.lhy.commonweb.service.AbstractService;
import com.lhy.commonweb.web.BaseControllerImpl;
import ${javaAttribute.model.fullName};
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
* @ClassName: ${javaAttribute.controller.name}
* @Description: ${javaAttribute.description}
* @author ${author}
* @date 2017年9月29日 上午11:03:30
* @Copyright: Copyright (c) 2017 wisedu
 */
@RestController
@RequestMapping(value = "${controllerAttribute.controllerRequestMapping}")
public class ${javaAttribute.controller.name} extends BaseControllerImpl<${javaAttribute.model.name}, ${javaAttribute.primaryKeyType}>{

	public ${javaAttribute.controller.name}(@Qualifier("${javaAttribute.service.parametterName}") AbstractService service) {
		super(service);
	}

	@Override
	public Page<${javaAttribute.model.name}> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute ${javaAttribute.model.name} bean) {
		super.wrapRequestPage(requestPage, "${controllerAttribute.orderBySql}");
		return super.query(requestPage, bean);
	}
 
}
