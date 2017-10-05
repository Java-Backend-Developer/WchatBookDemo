/**
 * Copyright 2014-2015 www.goujiawang.com
 * All rights reserved.
 * 
 * @project
 * @author jjw
 * @version 2.0
 * @date 2015-6-17
 */
package com.helper;

import java.util.Map;

public class ConfigInit {
	
	private static Map<String, String> configMap;

	public static Map<String, String> getConfigMap() {
		return configMap;
	}

	public static void setConfigMap(Map<String, String> configMap) {
		ConfigInit.configMap = configMap;
	}
	
		
	

}
