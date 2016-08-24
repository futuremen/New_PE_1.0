package com.future.hist.domain.form;

import com.hist.pe.entity.ExamPage;

public class ScoreForm {

	private Long studentNum;
	private String name;
	private ExamPage examPage;
	private double final_score;
	
	public Long getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(Long studentNum) {
		this.studentNum = studentNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "ScoreForm [studentNum=" + studentNum + ", name=" + name + ", examPage=" + examPage + ", final_score="
				+ final_score + "]";
	}
	
}
