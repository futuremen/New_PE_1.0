package com.hist.pe.utils;

import java.util.ArrayList;
import java.util.List;

import com.hist.pe.entity.FillQuestion;
import com.hist.pe.entity.JudgeQuestion;
import com.hist.pe.entity.Question;
import com.hist.pe.entity.SelectQuestion;

//类型转换器
public class TypeConversion {
    
	private static Question question;
	private static FillQuestion fquestion;
	private static JudgeQuestion jquestion;
	private static SelectQuestion squetion;
	//第一个是被转化成的对象第二个是得到的对象
	public static Object conversion(Object o1,Object o2){
		
		//o1是question对象
		if (o1 instanceof Question) {
			question=(Question)o1;
			System.out.println(question);
			//o2是填空题
			if(o2 instanceof FillQuestion){
				fquestion=(FillQuestion)o2;
				fquestion=new FillQuestion(question.getId(), question.getTitle(), question.getIssue(), question.getStandard_answer(), question.getQb(), question.getType(), question.getDegree(), question.getExamPages(),question.getScore());
			}
			//o2是jq
			else if(o2 instanceof JudgeQuestion){
				jquestion=(JudgeQuestion)o2;
				
			}
			//o2是sq
			else if(o2 instanceof SelectQuestion){
				squetion=(SelectQuestion)o2;
				String issue=question.getIssue();
				squetion=new SelectQuestion(question.getId(), question.getTitle(), question.getIssue(), question.getStandard_answer(), question.getQb(), question.getType(), question.getDegree(), question.getExamPages(),question.getScore());
				System.out.println("score : " + question.getScore());
				System.out.println("squetion : " + squetion.getScore());
				return squetion;
			}
		}
		
		//o1不是question
		else{
			question=(Question)o2;
			if(o1 instanceof SelectQuestion){
				squetion=(SelectQuestion)o1;
				String issure=squetion.getSelectOne()+"曱"+squetion.getSelectTwo()+"曱"+squetion.getSelectThree()+"曱"+squetion.getSelectFour();
				question=new Question(squetion.getId(), squetion.getTitle(), issure, squetion.getStandard_answer(), squetion.getQb(), squetion.getType(), squetion.getDegree(), squetion.getExamPage());
				return question;
			}
		}
		
		return null;
	}
	
	
	
}
