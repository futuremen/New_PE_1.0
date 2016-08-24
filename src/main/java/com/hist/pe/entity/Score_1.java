package com.hist.pe.entity;

import java.io.Serializable;

public class Score_1 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Student user;
	private ExamPage examPage;
	private double final_score;
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
	public Student getUser() {
		return user;
	}
	public void setUser(Student user) {
		this.user = user;
	}
	public ExamPage getExamPage() {
		return examPage;
	}
	public void setExamPage(ExamPage examPage) {
		this.examPage = examPage;
	}
	public double getFinal_score() {
		return final_score;
	}
	public void setFinal_score(double final_score) {
		this.final_score = final_score;
	}
	@Override
	public String toString() {
		return "Score [id=" + id + ", name=" + name + ", user=" + user + ", examPage=" + examPage + ", final_score="
				+ final_score + "]";
	}
	
}
