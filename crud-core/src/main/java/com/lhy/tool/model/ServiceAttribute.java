package com.lhy.tool.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceAttribute{
	
	/**
	 * 排序sql
	 */
	protected String orderBySql;
}
