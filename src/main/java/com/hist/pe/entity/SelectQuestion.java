package com.hist.pe.entity;

import java.util.List;

public class SelectQuestion {

	private Long id;
	private String title; // 题干
	private String issue;
	
	private String selectOne;
	private String selectTwo;
	private String selectThree;// 选项
	private String selectFour;
	private String standard_answer; // 标准答案
	private double score;
	
	private QuestionsBank qb; // 题库id
	private Type type; // 题型
	private Degree degree; // 难易程度id
	private Department department;
	
	private String answer;
	private List<ExamPage> examPage; // 试卷模板id
	
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
	public SelectQuestion(String title, String issue, String selectOne, String selectTwo, String selectThree,
			String selectFour, String standard_answer, double score, QuestionsBank qb,  Degree degree,
			Department department,Type type) {
		super();
		this.title = title;
		this.issue = issue;
		this.selectOne = selectOne;
		this.selectTwo = selectTwo;
		this.selectThree = selectThree;
		this.selectFour = selectFour;
		this.standard_answer = standard_answer;
		this.score = score;
		this.qb = qb;
		this.type=type;
		this.degree = degree;
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "SelectQuestion [id=" + id + ", title=" + title + ", issue=" + issue + ", selectOne=" + selectOne
				+ ", selectTwo=" + selectTwo + ", selectThree=" + selectThree + ", selectFour=" + selectFour
				+ ", standard_answer=" + standard_answer + ", score=" + score + ", qb=" + qb + ", type=" + type
				+ ", degree=" + degree + ", department=" + department + ", answer=" + answer + ", examPage=" + examPage
				+ "]";
	}
	public SelectQuestion() {
		super();
	}
	
	public SelectQuestion(Long id, String title, String issue, String standard_answer, QuestionsBank qb, Type type,
			Degree degree, List<ExamPage> examPage,double score) {
		super();
		this.id = id;
		this.title = title;
		this.issue = issue;
		this.standard_answer = standard_answer;
		this.qb = qb;
		this.type = type;
		this.degree = degree;
		this.examPage = examPage;
		this.score = score;

		String[] options = issue.split("曱");
		this.selectOne = options[0];
		this.selectTwo = options[1];
		this.selectThree = options[2];
		this.selectFour = options[3];
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

	public String getSelectOne() {
		return selectOne;
	}

	public void setSelectOne(String selectOne) {
		this.selectOne = selectOne;
	}

	public String getSelectTwo() {
		return selectTwo;
	}

	public void setSelectTwo(String selectTwo) {
		this.selectTwo = selectTwo;
	}

	public String getSelectThree() {
		return selectThree;
	}

	public void setSelectThree(String selectThree) {
		this.selectThree = selectThree;
	}

	public String getSelectFour() {
		return selectFour;
	}

	public void setSelectFour(String selectFour) {
		this.selectFour = selectFour;
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

	public double getScore() {
		return score;
	} 

	public void setScore(double score) {
		this.score = score;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	

}
