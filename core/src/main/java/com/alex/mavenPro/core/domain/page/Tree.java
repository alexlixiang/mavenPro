package com.alex.mavenPro.core.domain.page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 树形结构的数据封装，方便进行递归操作
 * 
 * @author 谢哲
 * 
 */
public class Tree implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String text;
	private List<Tree> children;
	private Map<String, Object> attributes;// 其他属性
	private int parentId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
