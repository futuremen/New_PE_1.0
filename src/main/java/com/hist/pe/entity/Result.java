package com.hist.pe.entity;
/**
 * 考试结果
 * @author wangshigen
 */
public class Result {

	private Long id;
	private String name;
	private String simeple_answer;         //单选答案
	private String mul_answer;              //多选答案
	private String fill_answer;              //填空答案
	private String judge_answer;              //多选答案
	private ExamPage examPage;
	private double final_score;            //最终得分
	private Student user;
	
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

	public String getSimeple_answer() {
		return simeple_answer;
	}

	public void setSimeple_answer(String simeple_answer) {
		this.simeple_answer = simeple_answer;
	}

	public String getMul_answer() {
		return mul_answer;
	}

	public void setMul_answer(String mul_answer) {
		this.mul_answer = mul_answer;
	}

	public String getFill_answer() {
		return fill_answer;
	}

	public void setFill_answer(String fill_answer) {
		this.fill_answer = fill_answer;
	}

	public String getJudge_answer() {
		return judge_answer;
	}

	public void setJudge_answer(String judge_answer) {
		this.judge_answer = judge_answer;
	}

	public ExamPage getExamPage() {
		return examPage;
	}

	public void setExamPage(ExamPage examPage) {
		this.examPage = examPage;
	}

	public Student getUser() {
		return user;
	}

	public void setUser(Student user) {
		this.user = user;
	}

	public double getFinal_score() {
		return final_score;
	}

	public void setFinal_score(double final_score) {
		this.final_score = final_score;
	}

	@Override
	public String toString() {
		return "Result [id=" + id + ", name=" + name + ", simeple_answer=" + simeple_answer + ", mul_answer="
				+ mul_answer + ", fill_answer=" + fill_answer + ", judge_answer=" + judge_answer + ", examPage="
				+ examPage + ", final_score=" + final_score + ", user=" + user + "]";
	}

	public Result() {
	}
}
