package ${javaAttribute.controller.packageName};

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ${javaAttribute.model.fullName};
import ${javaAttribute.pageModel.fullName};
import ${javaAttribute.service.fullName};
import com.wisedu.zzfw.template.common.model.ResponseList;
import com.wisedu.zzfw.template.common.model.ResponseResult;
import com.wisedu.zzfw.template.common.util.ResponseResultFactory;

/**
 * 
* @ClassName: ${javaAttribute.controller.name}
* @Description: ${javaAttribute.description}
* @author  hyluan
* @date 2017年9月29日 上午11:03:30
* @Copyright: Copyright (c) 2017 wisedu
 */
@Controller
@RequestMapping(value = "${controllerAttribute.controllerRequestMapping}")
public class ${javaAttribute.controller.name}{

	@Autowired
	private ${javaAttribute.service.name} ${javaAttribute.service.parametterName};
	
	@Autowired
	private ResponseResultFactory responseResultFactory;
	
	@RequestMapping(value = "view")
	public String view(){
		return "${controllerAttribute.viewPath}";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value = "list",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseList<${javaAttribute.model.name}> list(${javaAttribute.pageModel.name} ${javaAttribute.pageModel.parametterName}) {
		return ${javaAttribute.service.parametterName}.selectPage(${javaAttribute.pageModel.parametterName});
	}

	@RequestMapping(value = "add",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<Object> add(${javaAttribute.model.name} ${javaAttribute.model.parametterName}){
		return responseResultFactory.getSuccessObject(${javaAttribute.service.parametterName}.insertSelective(${javaAttribute.model.parametterName}));
	}

	@RequestMapping(value = "get",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<${javaAttribute.model.name}> get(@RequestParam(required=true) String id) {
		return responseResultFactory.getSuccessObject(${javaAttribute.service.parametterName}.selectByPrimaryKey(id));
	}

	@RequestMapping(value = "delete",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<Object> delete(@RequestParam(required=true) String id){
		return responseResultFactory.getSuccessObject(${javaAttribute.service.parametterName}.deleteByPrimaryKey(id));
	}
	
	@RequestMapping(value = "update",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<Object> update(${javaAttribute.model.name} ${javaAttribute.model.parametterName}){
		return responseResultFactory.getSuccessObject(${javaAttribute.service.parametterName}.updateByPrimaryKey(${javaAttribute.model.parametterName}));
	}
	
	@RequestMapping(value = "exist",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<Object> exist(${javaAttribute.model.name} ${javaAttribute.model.parametterName}){
		int count  = ${javaAttribute.service.parametterName}.exist(${javaAttribute.model.parametterName});
		if(count>0){
			return responseResultFactory.getSuccessObject();
		}else{
			return responseResultFactory.getFailObject("该数据不存在");
		}
	}
 
}
