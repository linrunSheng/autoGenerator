package com.wisedu.zzfw.model;

import java.util.List;

import com.wisedu.zzfw.autoconfigation.GeneratorProperties.ModelAttributes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CrudBean {
	
	private String description;
	
	private String simpleName;
	
	private String fullName;
	
	private List<CrudColumn> columns;
	
	private ModelAttributes modelAttributes;
	
}
