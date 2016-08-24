package com.hist.pe.entity;

public class JudgeQuestion {

	
	private Long id;
	private String title;       //题干
	
	@Deprecated
	private String issue;       //在这里用不上 ，只用判断对错就行了
	
	private String standard_answer;   //标准答案
	private QuestionsBank qb;           // 题库id
	private Type type;           //题型
	private Degree degree;         //难易程度id
	private ExamPage examPage;       //试卷模板id
	private Department department;
	
	private String answer;     //所答试题的答案；
	
	private double score;   //每道题的分数
	
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
	public ExamPage getExamPage() {
		return examPage;
	}
	public void setExamPage(ExamPage examPage) {
		this.examPage = examPage;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
	
	
	public JudgeQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public JudgeQuestion(String title, String standard_answer, QuestionsBank qb, Type type, Degree degree,
			Department department) {
		super();
		this.title = title;
		this.standard_answer = standard_answer;
		this.qb = qb;
		this.type = type;
		this.degree = degree;
		this.department = department;
	}
	@Override
	public String toString() {
		return "JudgeQuestion [id=" + id + ", title=" + title + ", issue=" + issue + ", standard_answer="
				+ standard_answer + ", qb=" + qb + ", type=" + type + ", degree=" + degree + ", examPage=" + examPage
				+ ", answer=" + answer + ", score=" + score + "]";
	}
	
}
