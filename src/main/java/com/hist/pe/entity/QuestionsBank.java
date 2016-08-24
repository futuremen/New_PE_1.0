package com.hist.pe.entity;
/**
 * 题库
 * @author wangshigen
 *
 */
public class QuestionsBank {

	private Long id;
	private String name; 
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
		return "QuestionsBank [id=" + id + ", name=" + name + "]";
	}
	
}
