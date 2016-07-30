package com.alex.mavenPro.core.domain.page;


import com.alex.mavenPro.core.common.util.PageHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 有分页列表形式的数据
 * 
 * @author 谢哲
 * 
 */
public class Grid implements Serializable {

	private static final long serialVersionUID = 1L;

	private long total = 0L;
	private List rows = new ArrayList();
	private PageHelper page;// 返回分页信息
	private boolean success = true;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public PageHelper getPage() {
		return page;
	}

	public void setPage(PageHelper page) {
		this.page = page;
	}

}
