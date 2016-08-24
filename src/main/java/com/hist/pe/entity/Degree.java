package com.hist.pe.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 试卷难易程度
 * @author wangshigen
 *
 */
public class Degree {
	
	private Long d_id;
	private String d_degree;
	
	
  	public static final String DEGREE_A = "简单";
	public static final String DEGREE_B = "一般";
	public static final String DEGREE_C = "较难";
	public Degree() {
		super();
	}
	
	public Long getD_id() {
		return d_id;
	}
	
	public void setD_id(Long d_id) {
		this.d_id = d_id;
	}

	public String getD_degree() {
		return d_degree;
	}

	public void setD_degree(String d_degree) {
		this.d_degree = d_degree;
	}

	@Override
	public String toString() {
		return "Degree [d_id=" + d_id + ", d_degree=" + d_degree + "]";
	}
		
}
