package com.hist.pe.entity;

import java.util.List;

public class FillQuestion {

	private Long id;
	private String title;       //题干
	private String standard_answer;   //标准答案
	
	
	private String issue;       //在这里用不上
	
	private QuestionsBank qb;           // 题库id
	private Department department;
	private Type type;           //题型
	private Degree degree;         //难易程度id
	private List<ExamPage> examPage;       //试卷模板id
	
	private double score;   //试题分数
	
	
	@Override
	public String toString() {
		return "FillQuestion [id=" + id + ", title=" + title + ", issue=" + issue + ", standard_answer="
				+ standard_answer + ", qb=" + qb + ", type=" + type + ", degree=" + degree + ", examPage=" + examPage
				+ ", score=" + score + "]";
	}
	public FillQuestion() {
		super();
	}
	
	
	public FillQuestion(String title, String standard_answer, QuestionsBank qb, Department department, Type type,
			Degree degree, double score) {
		super();
		this.title = title;
		this.standard_answer = standard_answer;
		this.qb = qb;
		this.department = department;
		this.type = type;
		this.degree = degree;
		this.score = score;
	}
	public FillQuestion(Long id, String title, String issue, String standard_answer, QuestionsBank qb, Type type,
			Degree degree, List<ExamPage> exampages,double score) {
		super();
		this.id = id;
		this.title = title;
		this.issue = issue;
		this.standard_answer = standard_answer;
		this.qb = qb;
		this.type = type;
		this.degree = degree;
		this.examPage = exampages;
		this.score = score;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getStandard_answer() {
		return standard_answer;
	}
	public void setStandard_answer(String standard_answer) {
		this.standard_answer = standard_answer;
	}
	public QuestionsBank getQb() {
		return qb;
	}
	public void setQb(QuestionsBank qb) {
		this.qb = qb;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Degree getDegree() {
		return degree;
	}
	public void setDegree(Degree degree) {
		this.degree = degree;
	}
	public List<ExamPage> getExamPage() {
		return examPage;
	}
	
	public void setExamPage(List<ExamPage> examPage) {
		this.examPage = examPage;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
}
