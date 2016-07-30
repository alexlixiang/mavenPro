package com.alex.mavenPro.core.common.util;

import java.io.Serializable;

/**
 * 分页帮助类
 * 
 * @author 谢哲
 * 
 */
public class PageHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private int page = 1; // 当前页
	private int rows = 10; // 每页显示条数
	private String sort; // 排序字段
	private String order; // 排序规则
	private long total = 0L; // 数据总数
	private int pageCount = 1; // 总页数

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageCount() {
		if (total <= 0)
			pageCount = 1;
		else {
			if (rows < 1)
				rows = 1;
			pageCount = (int)((total + rows - 1) / rows);
		}
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
