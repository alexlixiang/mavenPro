package com.alex.mavenPro.core.domain;


import com.alex.mavenPro.core.common.Const;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: lx Date: 14-9-5 Time: 上午9:31 To change this template use File | Settings | File Templates.
 */
@MappedSuperclass
public class BaseModel {

	private Date updateTime = new Date();
	private Date createTime = new Date();
	private String modelState = Const.MODEL_STATE.NORMAL.toString();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true, length = 7)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = false, length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "model_state")
	public String getModelState() {
		return modelState;
	}

	public void setModelState(String modelState) {
		this.modelState = modelState;
	}

	@PrePersist
	void onCreate() {
		this.setCreateTime(new Date());
	}

	@PreUpdate
	void onPersist() {
		this.setUpdateTime(new Date());
	}

}
