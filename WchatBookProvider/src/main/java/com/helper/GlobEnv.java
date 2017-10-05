/**
 * Copyright 2014-2015 goujiawang.com
 * All rights reserved.
 * 
 * @project
 * @author Flouny.Caesar
 * @version 2.0
 * @date 2014-05-12
 */
package com.helper;


/**
 * 获取静态/动态资源
 * @author Flouny.Caesar
 *
 */
public class GlobEnv {

	/**
	 * 根据key获取value
	 * @param key
	 * @return value
	 */
	public static String get(String key) {

		return ConfigInit.getConfigMap().get(key);
	}
}