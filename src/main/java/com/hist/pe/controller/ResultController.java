package com.hist.pe.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.future.hist.domain.form.ScoreForm;
import com.future.hist.serviceinter.ExamineService;
import com.future.hist.serviceinter.ResultService;
import com.future.hist.serviceinter.ScoreService;
import com.future.hist.serviceinter.StudentService;
import com.hist.pe.entity.ExamPage;
import com.hist.pe.entity.Score_1;
import com.hist.pe.entity.Student;


/**
 * 成绩管理
 * @author wangshigen
 *
 */
@Controller
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	@Autowired
	private ExamineService examineSerive;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ScoreService scoreService;
	
	@RequestMapping("/teacher/releaseResult")
	public String releaseResult(Model model){
		List<ExamPage> examPages = resultService.getAllExampages();
		model.addAttribute("examPages",examPages);
		model.addAttribute("scoreForm",new ScoreForm());
		System.out.println("发布成绩！");
		return "teacher/releaseResult";
	}
	
	@RequestMapping(value="/teacher/result",method=RequestMethod.POST)
	public String result(@ModelAttribute ScoreForm scoreForm){
		System.out.println("scoreForm : " + scoreForm);
		ExamPage examPage = examineSerive.getExamPageById(scoreForm.getExamPage().getId());
		Score_1 score = new Score_1();
		System.out.println("examPage : " + examPage);
		Student u = studentService.getUserBySNumAndName(scoreForm.getStudentNum(),scoreForm.getName());
		System.out.println("user : " + u);
		if(u != null && examPage != null){
			score.setUser(u);
			score.setExamPage(examPage);
			score.setFinal_score(scoreForm.getFinal_score());
			scoreService.save(score);
		}
		
		return "teacher/result";
	}
	
	@RequestMapping("/student/findScore/{id}")
	public String findSore(@PathVariable("id") Long id,Map<String,Object> map){
		List<Score_1> scores = scoreService.getScoreByUserId(id);
	//	System.out.println("scores : " + scores.get(0).getUser().getStudentNum());
		map.put("scores", scores);
		return "student/findScore";
	}
	
	@RequestMapping("/student/findAllScore/{id}")
	public String findAllScoreByExamPageId(@PathVariable("id") Long id, Map<String,Object> map){
		List<Score_1> scores = scoreService.findAllScoreByExamPageId(id);
		System.out.println("score.get(0) : " + scores.get(0));
		map.put("scores", scores);
		map.put("exampageId", id);
		return "student/findAllScore";
	}
	
//	//分页之后的删除和修改效果添加后还要回到原来的页面
//		@RequestMapping(value="list/{currentPage}")
//		public String list(@PathVariable("currentPage") String currentPage, Map<String, Object> map){
//			
//			//List<Notice> noticeList = noticeMapper.findAll();
//			//map.put("noticeList", noticeList);
//			Page page = new Page();
//			System.out.println("当前的页数是----》"+currentPage);
//			if(currentPage == null || "".equals(currentPage.trim())){
//				
//				currentPage = 1 + "";
//			}
//			
//			Integer totalNumber = scoreService.getCount();
//			page.setTotalNumber(totalNumber);
//			page.setCurrentPage(Integer.valueOf(currentPage));
//			
//			List<Score> scoreList = scoreService.getList(page);
//			
//			map.put("scoreList", scoreList);
//			map.put("page", page);
//			System.out.println("page-->对象"+page + "noticeList-->对象"+ scoreList);
//			return "notice/list";
//		}
}
