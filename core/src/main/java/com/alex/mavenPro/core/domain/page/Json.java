package com.alex.mavenPro.core.domain.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * ajax请求返回普通数据封装
 * 
 * modefy by xq
 * 
 * @author 谢哲
 * 
 */
public class Json implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success = true;// 成功失败标识
	private String message;// message信息
	private Map<String, Object> map;// 返回数据对象
	private Object object;

	public Json() {
	}

	public Json(String msg) {
		this.message = msg;
	}

	public void put(String key, Object value) {
		if (map == null)
			map = new HashMap<String, Object>();
		map.put(key, value);
	}

	public Object get(String key) {
		if (map == null)
			return null;
		return map.get(key);
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
