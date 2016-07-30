package com.alex.mavenPro.core.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据封装类
 * @author xq
 *
 */
public class Results {
	
	private String _flag="fialed";
	private Map<String,Object> _map;
	
	public Results(){}
	public Results(String flag){
		_flag = flag;
	}
	public String getFlag() {
		return _flag;
	}
	public void setFlag(String flag) {
		_flag = flag;
	}
	public Map<String, Object> getMap() {
		return _map;
	}
	
	public Object get(String key){
		return _map.get(key);
	}
	
	public boolean put(String key,String val){
		try {
			if(_map.isEmpty()){
				_map = new HashMap<String,Object>();
			}
			_map.put(key, val);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void setMap(Map<String, Object> map) {
		_map = map;
	}
	
}
