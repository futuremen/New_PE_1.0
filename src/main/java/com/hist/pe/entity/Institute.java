package com.hist.pe.entity;

import java.io.Serializable;
import java.util.List;



public class Institute extends BaseEntity<Institute> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String instituteName;
	private String instituteNumber;
	private String instituteRemarks;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getInstituteNumber() {
		return instituteNumber;
	}
	public void setInstituteNumber(String instituteNumber) {
		this.instituteNumber = instituteNumber;
	}
	public String getInstituteRemarks() {
		return instituteRemarks;
	}
	public void setInstituteRemarks(String instituteRemarks) {
		this.instituteRemarks = instituteRemarks;
	}
	
	
	
}
