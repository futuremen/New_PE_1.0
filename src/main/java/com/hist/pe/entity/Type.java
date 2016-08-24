package com.hist.pe.entity;

/**
 * 题型
 * @author wangshigen
 *
 */
public class Type {

	private Long id;
	private String name;
	
	public static final String SINGLE_CHOICE = "单项选择题";
	public static final String MANY_CHOICE = "多项选择题";
	public static final String JUDGMENT = "判断题";
	public static final String FILL_IN_THE_BLANKS = "填空题";
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + "]";
	}
	
}
