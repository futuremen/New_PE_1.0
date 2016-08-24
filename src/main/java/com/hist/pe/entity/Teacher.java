package com.hist.pe.entity;

import java.io.Serializable;
import java.util.List;



public class Teacher extends BaseEntity<Teacher> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String account;
	private String name;
	private String password;
	private String roleids;
	private String sex;
	private String institute_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleids() {
		return roleids;
	}
	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(String institute_id) {
		this.institute_id = institute_id;
	}

	

}
