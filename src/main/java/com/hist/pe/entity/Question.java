package com.hist.pe.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 试题
 * @author wangshigen
 *
 */
public class Question {

	private Long id;
	private String title;       //题干
	private String issue;       //题的内容
	private String standard_answer;   //标准答案
	private QuestionsBank qb;           // 题库id
	private Type type;           //题型
	private Degree degree;         //难易程度id
	private List<ExamPage> examPages = new ArrayList<ExamPage>();       //试卷模板id
	private Department department; //系别
	private double score;   //分值
	
	
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
	
	public Question() {
		super();
	}
	public Question(Long id, String title, String issue, String standard_answer, QuestionsBank qb, Type type,
			Degree degree, List<ExamPage> examPage) {


		super();
		this.id = id;
		this.title = title;
		this.issue = issue;
		this.standard_answer = standard_answer;
		this.qb = qb;
		this.type = type;
		this.degree = degree;
		this.examPages = examPage;


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
	
	public List<ExamPage> getExamPages() {
		return examPages;
	}
	public void setExamPages(List<ExamPage> examPages) {
		this.examPages = examPages;
	}


	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", issue=" + issue + ", standard_answer=" + standard_answer
				+ ", qb=" + qb + ", type=" + type + ", degree=" + degree + ", examPages=" + examPages + ", department="
				+ department + ", score=" + score + "]";
	}

}
