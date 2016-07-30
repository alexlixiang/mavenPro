package com.alex.mavenPro.core.domain;


import com.alex.mavenPro.core.common.util.EncryptionUtil;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA. User: lx Date: 14-9-2 Time: 下午3:27 To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_user")
public class User extends BaseModel {

	private int id;
	private String userName;
	private String password;

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "pwd")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMD5Password(String password) {
		try {
			this.password = EncryptionUtil.md5Encode(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
