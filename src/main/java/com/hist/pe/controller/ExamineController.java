package com.hist.pe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.future.hist.serviceinter.ExamineService;
import com.future.hist.serviceinter.QuestionService;
import com.future.hist.serviceinter.ResultService;
import com.future.hist.serviceinter.ScoreService;
import com.future.hist.serviceinter.StudentService;
import com.hist.pe.entity.ExamPage;
import com.hist.pe.entity.JudgeQuestion;
import com.hist.pe.entity.Question;
import com.hist.pe.entity.Result;
import com.hist.pe.entity.Score;
import com.hist.pe.entity.Score_1;
import com.hist.pe.entity.SelectQuestion;
import com.hist.pe.entity.Type;
import com.hist.pe.service.OfficeService;
import com.hist.pe.entity.Student;
import com.hist.pe.utils.SplitAnswerUtils;
import com.hist.pe.utils.TypeConversion;
import com.hist.pe.utils.UserUtils;

/**
 * 考试管理
 * 
 * @author wangshigen
 *
 */
@Controller
public class ExamineController {

	@Autowired
	private ExamineService examineService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private ResultService resultService;

	@Autowired
	private ScoreService scoreService;

	@RequestMapping(value = "/student/examine/{examPage_id}", method = RequestMethod.GET)
	public String startExamine(@PathVariable("examPage_id") Long examPage_id, Map<String, Object> map,
			HttpSession session) {
		System.out.println("开始考试");
		System.out.println("examPage_id : " + examPage_id);
		List<Question> questions = questionService.getQuestionsByExamPageId(examPage_id);
		System.out.println(questions.size());
		ExamPage examPage = examineService.getExamPageById(examPage_id);
		System.out.println("examPage.getExamTime :" + examPage.getExamTime());
		List<SelectQuestion> selectQuestions = new ArrayList<SelectQuestion>();
		List<SelectQuestion> selectMulQuestions = new ArrayList<SelectQuestion>();
		List<Question> judgeQuestions = new ArrayList<Question>();
		List<Question> fillQuestions = new ArrayList<Question>();
		for (int i = 0; i < questions.size(); i++) {
			System.out.println("questions.get(i).getType().getName() : " + questions.get(i).getType().getName());
			if (questions.get(i).getType().getName().equals(Type.SINGLE_CHOICE)) { // 单项选择
				SelectQuestion selectQuestion = new SelectQuestion();
				SelectQuestion selectq = (SelectQuestion) TypeConversion.conversion(questions.get(i), selectQuestion);
				System.out.println("selectq : " + selectq.getSelectOne() + selectq.getSelectTwo());
				selectQuestions.add(selectq);
			}
			if (questions.get(i).getType().getName().equals(Type.MANY_CHOICE)) { // 多项选择
				SelectQuestion selectMulQuestion = new SelectQuestion();
				SelectQuestion selectMulq = (SelectQuestion) TypeConversion.conversion(questions.get(i),
						selectMulQuestion);
				selectMulQuestions.add(selectMulq);
			}
			if (questions.get(i).getType().getName().equals(Type.JUDGMENT)) { // 判断题
				System.out.println("questions.get(i) : " + questions.get(i));
				judgeQuestions.add(questions.get(i));
			}
			if (questions.get(i).getType().getName().equals(Type.FILL_IN_THE_BLANKS)) { // 填空题
				fillQuestions.add(questions.get(i));
			}
		}
		map.put("result", new Result());
		map.put("examPage", examPage);
		session.setAttribute("selectQuestions", selectQuestions);
		session.setAttribute("selectMulQuestions", selectMulQuestions);
		session.setAttribute("judgeQuestions", judgeQuestions);
		session.setAttribute("fillQuestions", fillQuestions);
		return "Student/examine";
	}

	@RequestMapping("/student/assignment")
	@SuppressWarnings("unchecked")
	public String assignment(@ModelAttribute Result result, HttpSession session) {
		System.out.println("result : " + result);
		ExamPage examPage = examineService.getExamPageById(result.getExamPage().getId());

		List<SelectQuestion> simpleQuesions = (List<SelectQuestion>) session.getAttribute("selectQuestions");
		List<SelectQuestion> mulQuestions = (List<SelectQuestion>) session.getAttribute("selectMulQuestions");
		List<Question> judgeQuestions = (List<Question>) session.getAttribute("judgeQuestions");
		List<Question> fillQuestions = (List<Question>) session.getAttribute("fillQuestions");

		double final_score = 0;

		if (simpleQuesions.size() > 0 || !"".equals(result.getSimeple_answer())) {
			System.out.println("simpleQuesions.size() : " + simpleQuesions.size());
			System.out.println("result.getSimeple_answer() : " + result.getSimeple_answer());
			List<SelectQuestion> simpleAnswer = SplitAnswerUtils.splitSelectQuestion(result.getSimeple_answer());
			for (int i = 0; i < simpleQuesions.size(); i++) {
				if (simpleAnswer.get(i).getAnswer().equals(simpleQuesions.get(i).getStandard_answer())) {
					System.out.println("simpleQuesions.get(i).getStandard_answer() : "
							+ simpleQuesions.get(i).getStandard_answer());
					final_score += simpleQuesions.get(i).getScore();
				}
			}
		}

		if (mulQuestions.size() > 0 || !result.getMul_answer().equals("")) {
			List<SelectQuestion> mulAnswer = SplitAnswerUtils.splitSelectQuestion(result.getMul_answer());
			for (int i = 0; i < simpleQuesions.size(); i++) {
				if (mulAnswer.get(i).getAnswer().equals(mulQuestions.get(i).getStandard_answer())) {
					System.out.println("mulQuestions.get(i).getScore() : " + mulQuestions.get(i).getScore());
					System.out.println(
							"mulQuestions.get(i).getStandard_answer() : " + mulQuestions.get(i).getStandard_answer());
					final_score += mulQuestions.get(i).getScore();
				}
			}
		}
		if (judgeQuestions.size() > 0 || !result.getJudge_answer().equals("")) {
			List<JudgeQuestion> judgeAnswer = SplitAnswerUtils.splitJudgeQuestionOption(result.getJudge_answer());
			for (int i = 0; i < judgeQuestions.size(); i++) {
				if (judgeAnswer.get(i).getAnswer().equals(judgeQuestions.get(i).getStandard_answer())) {
					System.out.println("judgeQuestions.get(i).getStandard_answer() : "
							+ judgeQuestions.get(i).getStandard_answer());
					final_score += judgeQuestions.get(i).getScore();
				}
			}
		}

		System.out.println("judge.size : " + judgeQuestions.size());
		System.out.println("final_score : " + final_score);
		result.setFinal_score(final_score);
		System.out.println("result : " + result);
		// TODO
		Student user = UserUtils.getUser().getStudent();
		// user.setId(UserUtils.getUser().getId());
		System.out.println("user : " + user);
		Score_1 score = new Score_1();
		score.setExamPage(examPage);
		score.setFinal_score(final_score);
		score.setUser(user);
		session.setAttribute("result", result);

		String term_id = OfficeService.getCurrentTerm().getId();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("student_id", user.getId());
		map.put("term_id", term_id);
		map.put("onlineScore", result.getFinal_score());

		List<Score> scores = OfficeService.getStudentScore(map);

		if (scores.size() == 0) {

			OfficeService.insertOnlineScore(map);

		} else {

			map.clear();

			String score_id = scores.get(0).getId();

			map.put("onlineScore", result.getFinal_score());

			map.put("score_id", score_id);

			OfficeService.updateOnlineScore(map);
		}

		return "redirect:/student/exmineFinished";
	}

	@RequestMapping("/student/exmineFinished")
	public String exmineFinished() {

		return "Student/assignment";
	}

	@RequestMapping("/getExample")
	public String getExample(Map<String, Object> map) {
		List<ExamPage> examPages = examineService.getExamPages();
		System.out.println("examPages : " + examPages.get(0));
		map.put("examPages", examPages);
		System.out.println("map : " + map.size());
		return "success";
	}

}
