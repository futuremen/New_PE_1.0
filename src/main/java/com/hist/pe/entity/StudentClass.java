package com.hist.pe.entity;

import java.io.Serializable;

public class StudentClass extends BaseEntity<StudentClass> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id ;
	
	private String major;

	private String name;
	
	private String number;
	
	private String institute_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getInstitute_id() {
		return institute_id;
	}

	public void setInstitute_id(String institute_id) {
		this.institute_id = institute_id;
	}
	




}
