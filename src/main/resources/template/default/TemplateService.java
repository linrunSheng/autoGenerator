package ${javaAttribute.service.packageName};

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import ${javaAttribute.model.fullName};
import ${javaAttribute.pageModel.fullName};
import com.wisedu.zzfw.template.common.model.ResponseList;
import com.wisedu.zzfw.template.common.service.AbstractCommonService;
import com.wisedu.zzfw.template.common.util.JacksonUtil;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 
* @ClassName: ${javaAttribute.service.name}
* @Description: ${javaAttribute.description}
* @author  hyluan
* @date 2017年12月18日 下午5:08:53
* @Copyright: Copyright (c) 2017 wisedu
 */
@Service
public class ${javaAttribute.service.name} extends AbstractCommonService<${javaAttribute.model.name}>{

	public ${javaAttribute.service.name}(@Qualifier("${javaAttribute.model.parametterName}Mapper") Mapper<${javaAttribute.model.name}> mapper) {
		super(mapper);
	}

	public ResponseList<${javaAttribute.model.name}> selectPage(${javaAttribute.pageModel.name} ${javaAttribute.pageModel.parametterName}){
		PageHelper.startPage(${javaAttribute.pageModel.parametterName}.getPage(), ${javaAttribute.pageModel.parametterName}.getRows());
		Example example = new Example(${javaAttribute.model.name}.class);
		Criteria criteria = example.createCriteria();
		<#list columns as pn> 
			<#if pn.columnAttributes.queryable?string('true','false') =='true'>
		if(!StringUtils.isEmpty(${javaAttribute.pageModel.parametterName}.get${pn.columnName?cap_first}())){
			criteria.andLike(${javaAttribute.model.name}.FieldEnum.${pn.dbColumnName}.javaFieldName(),"%"+${javaAttribute.pageModel.parametterName}.get${pn.columnName?cap_first}()+"%");
		}
			</#if>
		</#list> 
		example.setOrderByClause(" ${serviceAttribute.orderBySql} ");
		Page<${javaAttribute.model.name}> data = (Page<${javaAttribute.model.name}>)super.getMapper().selectByExample(example);
		return JacksonUtil.getTableJSON(data.getTotal(), data);
	}
	
	public ${javaAttribute.model.name} selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}
	
	public int insertSelective(${javaAttribute.model.name} ${javaAttribute.model.parametterName}) {
		return super.insertSelective( ${javaAttribute.model.parametterName});
	}
	
	public int deleteByPrimaryKey(String id) {
		return super.deleteByPrimaryKey(id);
	}
	
	public int updateByPrimaryKey(${javaAttribute.model.name} ${javaAttribute.model.parametterName}) {
		return super.updateByPrimaryKey( ${javaAttribute.model.parametterName});
	}
	
	public int exist(${javaAttribute.model.name} ${javaAttribute.model.parametterName}){
		return super.selectCount(${javaAttribute.model.parametterName});
	}


}
