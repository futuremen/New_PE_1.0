package com.hist.pe.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 试卷模板
 * @author wangshigen
 *
 */
public class ExamPage {

	private Long id;
	private String name;
	private String description;
	private int subject ;//所属科目
	private int examTime;        //考试时间
	private int total_score;     //试卷总分
	private int questionnumber;//题数
	public List<Question> questions=new ArrayList<Question>();
	private Degree dg;//试卷的难度
	private Department department;
	
	private double score_degree_A; //简单题型的分数
	private double score_degree_B; //一般题型的分数
	private double score_degree_C; //困难提醒的分数
	
	private double percent_A; //简单类型所占百分比
	private double percent_B; //一般类型所占百分比
	private double percent_C;//较难类型所占百分比
	
	private static final int DEGREE_A = 1; //简单
	private static final int DEGREE_B = 2; //一般
	private static final int DEGREE_C = 3; //困难
	
	//自动生成试卷
	public void uoMakeFinish(List<Question> easy,List<Question> common,List<Question> diffcult) {
		if(dg.getD_id()==DEGREE_A){
			percent_A=0.6;
			percent_B=0.3;
			percent_C=0.1;	
		}
		if (dg.getD_id()==DEGREE_B) {
			percent_A=0.3;
			percent_B=0.5;
			percent_C=0.2;	
		}
		if (dg.getD_id()==DEGREE_C) {
			percent_A=0.3;
			percent_B=0.4;
			percent_C=0.3;
		}
		score_degree_A=total_score*percent_A; //得到简单题型的分数
		score_degree_B=total_score*percent_B; //得到一般题型的分数
		score_degree_C=total_score*percent_C; //得到困难题型的分数
		
		/**
		 * 
		 */
		addQuestions(score_degree_A, easy);
		addQuestions(score_degree_B, common);
		addQuestions(score_degree_C, diffcult);
	}
	
	private void addQuestions(double score1,List<Question> questions){
		double score=0;
		while(true){
			int rand=getRand(questions.size());
			score= score+questions.get(rand).getScore();
			System.out.println(score);
			if (score<=score1) {
				this.questions.add(questions.get(rand));
				questions.remove(questions.get(rand));
			}
			else{
   				score= score-questions.get(rand).getScore();
				if(score==score1){
					System.out.println(score);
					break;
				}
			}
		}
	}
	
	//得到范围内的随机数
	private int getRand(int range){
		int rand=(int) (Math.random()*range);
		return rand;
	}
	
	
	
	public double getScore_degree_A() {
		return score_degree_A;
	}
	

	public void setScore_degree_A(double score_degree_A) {
		this.score_degree_A = score_degree_A;
	}
	

	public double getScore_degree_B() {
		return score_degree_B;
	}
	

	public void setScore_degree_B(double score_degree_B) {
		this.score_degree_B = score_degree_B;
	}
	

	public double getScore_degree_C() {
		return score_degree_C;
	}
	

	public void setScore_degree_C(double score_degree_C) {
		this.score_degree_C = score_degree_C;
	}
	

	public int getQuestionnumber() {
		return questionnumber;
	}
	
	public void setQuestionnumber(int questionnumber) {
		this.questionnumber = questionnumber;
	}
	
	
	
	public Degree getDg() {
		return dg;
	}
	

	public void setDg(Degree dg) {
		this.dg = dg;
	}
	

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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getExamTime() {
		return examTime;
	}
	public void setExamTime(int examTime) {
		this.examTime = examTime;
	}
	public int getTotal_score() {
		return total_score;
	}
	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}
    
	public int getSubject() {
		return subject;
	}
	

	public void setSubject(int subject) {
		this.subject = subject;
	}
    
	
	public Department getDepartment() {
		return department;
	}
	

	public void setDepartment(Department department) {
		this.department = department;
	}
	

	

	@Override
	public String toString() {
		return "ExamPage [id=" + id + ", name=" + name + ", description=" + description + ", subject=" + subject
				+ ", examTime=" + examTime + ", total_score=" + total_score + ", questionnumber=" + questionnumber
				+ ", questions=" + questions + ", dg=" + dg + ", department=" + department + "]";
	}
	

	
}
