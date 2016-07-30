package com.alex.mavenPro.core.common.util;

import java.util.ResourceBundle;

/**
 * 读取classpath路径下的config.properties文件中的属性值
 * 
 * @author 谢哲
 * 
 */
public class ConfigUtil {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("config");

	public static final String getDefaultSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}

	public static final String getByName(String key) {
		return bundle.getString(key);
	}

}
