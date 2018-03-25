package com.lhy.tool.util;

import java.util.UUID;

/**
* @ClassName: UUIDUtil
* @Description: 
* @author  luanhy
* @date 2018年1月6日 下午1:00:55
* @Copyright: Copyright (c) 2017 wisedu
*/
public class UUIDUtil {
	
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
