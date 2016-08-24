
package com.hist.pe.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * 用户Entity
 * 
 * @author 刘鹏
 * @version 2015.12.1
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	private String account;

	private String password;

	private String[] roleids;

	public String getAccount() {

		if (account == null) {

			if (this.teacher == null) {
				account = this.student.getStudentAccount();
				id = this.student.getId();
				return account;
			} else {
				account = this.teacher.getAccount();
				id = this.teacher.getId();
				return account;
			}

		}
		return account;
	}

	public String getPassword() {

		if (password == null) {

			if (this.teacher == null) {
				password = this.student.getPassword();
				return password;
			} else {
				password = this.teacher.getPassword();
				return password;
			}

		}
		return password;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String[] getRoleids() {
		if (this.teacher == null) {
			roleids = this.student.getRole_id().split(",");
			return roleids;
		} else {
			roleids = this.teacher.getRoleids().split(",");
			return roleids;
		}
	
	}


	private Student student;

	private Teacher teacher;

	public User() {

	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}