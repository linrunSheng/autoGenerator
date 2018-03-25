package com.lhy.tool.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ControllerAttribute{
	
	private String controllerRequestMapping;
	
	private String viewPath;

	/**
	 * 排序sql
	 */
	protected String orderBySql;
}
