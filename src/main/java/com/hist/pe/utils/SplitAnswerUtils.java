package com.hist.pe.utils;

import java.util.ArrayList;
import java.util.List;

import com.hist.pe.entity.JudgeQuestion;
import com.hist.pe.entity.SelectQuestion;

public class SplitAnswerUtils {

	/**
	 * 分离选择题试题
	 * @param answer
	 * @return
	 */
	public static List<SelectQuestion> splitSelectQuestion(String answer){
		if (answer !=null || !"".equals(answer)) {
			
			List<SelectQuestion> list = new ArrayList<SelectQuestion>();
			String[] questionsAnswer = answer.split("&");
			System.out.println("str.length : " + questionsAnswer.length);
			for(int i = 0; i<questionsAnswer.length ;i++){
				list.add(splitQuestionOption(questionsAnswer[i]));
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 分离判断题试题
	 * @param answer
	 * @return
	 */
	public static List<JudgeQuestion> splitJudgeQuestionOption(String answer){
		List<JudgeQuestion> list = new ArrayList<JudgeQuestion>();
		String[] questionsAnswer = answer.split("&");
		System.out.println("str.length : " + questionsAnswer.length);
		for(int i = 0; i<questionsAnswer.length ;i++){
			list.add(splitJudgeQuestion(questionsAnswer[i]));
		}
		return list;
	}
	/**
	 * 分离选择题试题与选项
	 * @param questionOption
	 * @return
	 */
	private static SelectQuestion splitQuestionOption(String questionOption){
		String[] questionOptions = questionOption.split("☯");
		SelectQuestion select_question = new SelectQuestion();
		for(int i=0;i<questionOptions.length;i++){
			if (i== 0) {
				select_question.setId(Long.valueOf(questionOptions[i]));;
			}else{
				select_question.setAnswer(questionOptions[i]);
			}
		}
		
		return select_question;
	}
	
	/**
	 * 分离判断题试题与选项
	 * @param questionOption
	 * @return
	 */
	public static JudgeQuestion splitJudgeQuestion(String questionOption){
		
		JudgeQuestion judgeQuestion = new JudgeQuestion();
		
		String[] questionOptions = questionOption.split("☯");
		for(int i=0;i<questionOptions.length;i++){
			if (i== 0) {
				judgeQuestion.setId(Long.valueOf(questionOptions[i]));;
			}else{
				judgeQuestion.setAnswer(questionOptions[i]);
			}
		}
		return judgeQuestion;
	}
}
