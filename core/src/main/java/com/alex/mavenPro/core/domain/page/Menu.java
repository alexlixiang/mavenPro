package com.alex.mavenPro.core.domain.page;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	private String key;
	private String vals;
	private String path;
	private List<Menu> childs;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getVals() {
		return vals;
	}
	public void setVals(String vals) {
		this.vals = vals;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<Menu> getChilds() {
		return childs;
	}
	public void setChilds(List<Menu> childs) {
		this.childs = childs;
	}
}
