package com.lhy.tool.generator.enums;

import lombok.Getter;

/**
 * @ClassName: RequestMethodEnum
 * @Description: 
 * @author luanhy
 * @date 2018年1月6日 下午1:32:05
 * @Copyright: Copyright (c) 2017 wisedu
 */
@Getter
public enum RequestMethodEnum {
	VIEW("view"), GET("get"), LIST("list"), ADD("add"), DELETE("delete"), UPDATE("update"), EXIST("exist");

	String requestMethodName;

	RequestMethodEnum(String requestMethodName) {
		this.requestMethodName = requestMethodName;
	}
}
