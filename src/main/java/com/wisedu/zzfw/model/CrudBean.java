package com.wisedu.zzfw.model;

import java.util.List;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CrudBean {
	
	private String description;
	
	private String simpleName;
	
	private String fullName;
	
	private List<CrudColumn> columns;
	
	private ModelAttributes modelAttributes;
	
}
