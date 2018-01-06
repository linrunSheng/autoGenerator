package com.lhy.tool.model;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

/**
* @ClassName: SqlAttribute
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2018年1月6日 下午12:47:08
* @Copyright: Copyright (c) 2017 wisedu
*/
@Data
@Builder
public class SqlAttribute {
	
	String moduleWid;
	
	String moduleName;
	
	String parentModuleName;
	
	String menuitemWid;
	
	String roleName;
	
	List<Map<String,String>> modulepath;
}
