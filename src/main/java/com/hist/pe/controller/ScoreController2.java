package com.hist.pe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hist.pe.entity.Score;
import com.hist.pe.entity.Student;
import com.hist.pe.entity.StudentClass;
import com.hist.pe.entity.Term;
import com.hist.pe.entity.UtilScore;
import com.hist.pe.entity.WarpForScore;
import com.hist.pe.entity.WarpForScoreOnce;
import com.hist.pe.service.OfficeService;
import com.hist.pe.utils.UserUtils;
import com.sun.org.apache.regexp.internal.recompile;

@Controller
public class ScoreController2 {

	@RequestMapping(value = "manageScore")
	public String manageScore(ModelMap modelMap) {

//		Map<String, Object> map = new HashMap<String, Object>();
//
//		String teacher_id = UserUtils.getUser().getId();
//
//		map.put("teacher_id", teacher_id);
//
//		String class_id = OfficeService.getTeacherClsaaByPage(map).get(0).getId();
//		
//		
//
//		map.clear();
//
//		map.put("class_id", class_id);
//
//		modelMap.addAttribute("students", OfficeService.getClassStudent(map));

		return "Score/manageScore";
	}

	@RequestMapping(value = "hello")
	public @ResponseBody List<StudentClass> hello(String institute_id) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("institute_id", institute_id);

		List<StudentClass> x = OfficeService.getInstituteClass(map);

		return x;

	}

	@RequestMapping(value = "manageScore", method = RequestMethod.POST)
	public String manageScorePost(ModelMap modelMap, String class_id) {

		StudentClass studentClass = OfficeService.getStudentClassItem(class_id);

		modelMap.addAttribute("classItem", studentClass);

		return "Score/manageScore";
	}

	@RequestMapping(value = "viewClassScoreAjax")
	public @ResponseBody WarpForScoreOnce viewClassScoreAjax(String class_id, String term_id) {

		if (term_id.equals("")) {
			term_id = OfficeService.getCurrentTerm().getId();
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("class_id", class_id);

		map.put("studentClass_id", class_id);

		map.put("term_id", term_id);

		List<WarpForScore> wrForScores = new ArrayList<WarpForScore>();

		List<Student> students = OfficeService.getClassStudent(map);
		int count = 0;
		for (Student s : students) {
			count++;
			WarpForScore c = new WarpForScore();	
			map.clear();
			map.put("student_id", s.getId());
			map.put("term_id", term_id);
			List<Score> scores = OfficeService.getStudentScore(map);
			if (scores.size() != 0) {
				UtilScore utilScore = new UtilScore();
				utilScore.setWarpForScore(c);
				utilScore.setScore(scores.get(0));
				c = utilScore.getWarpForScore();
			}
			c.setId(count + "");
			c.setStudentAccount(s.getStudentAccount());
			c.setStudent_id(s.getId());
			c.setName(s.getName());
			c.setTerm_id(term_id);
			wrForScores.add(c);
		}
		WarpForScoreOnce ScoreOnce = new WarpForScoreOnce("1", "2", "13", wrForScores);
		return ScoreOnce;
	}

	@RequestMapping(value = "viewClassScore")
	public String viewClassScore(ModelMap modelMap, String class_id) {

		modelMap.addAttribute("class_id", class_id);
		
		String term_id = OfficeService.getCurrentTerm().getId();

		modelMap.addAttribute("term_id", term_id);
		
		
		return "Score/t";
	}

	@RequestMapping(value = "updateOrInsertScoreAjax")
	public @ResponseBody WarpForScore updateOrInsertScoreAjax(WarpForScore warpForScore) {

		UtilScore utilScore = new UtilScore();

		utilScore.setWarpForScore(warpForScore);

		Score score = utilScore.getScore();
		
		if (score.getId().equals("")) {
			
			score = utilScore.getScore();
			score = OfficeService.insertScore(score);
			warpForScore.setScore_id(score.getId());
			
		}else {
			
			OfficeService.updateScore(score);	
		}
	
		return warpForScore;
	}

	@RequestMapping(value = "getOneStudentScoreAjax")
	public @ResponseBody WarpForScore getOneStudentScoreAjax(WarpForScore warpForScore) {
		
		if (warpForScore.getScore_id().equals("")) {
			return warpForScore;
		}
		
		Score score = OfficeService.getScore(warpForScore.getScore_id());
		
	    UtilScore utilScore = new UtilScore();
		
		utilScore.setWarpForScore(warpForScore);
		
		utilScore.setScore(score);
		
		warpForScore = utilScore.getWarpForScore();
		
		Student student = OfficeService.getOneStudent(warpForScore.getStudent_id());
		warpForScore.setStudentAccount(student.getStudentAccount());
		warpForScore.setName(student.getName());
	
		
		return warpForScore;
	}
	@RequestMapping(value = "deleteOneStudentScoreAjax")
	public @ResponseBody Map<String, String> deleteOneStudentScoreAjax(String score_id) {
		
		OfficeService.deleteScore(score_id);
		
		Map<String, String>  map =new  HashMap<String, String>();
		map.put("ajaxResult", "success");
		return map; 
	}
	
	
	@RequestMapping(value = "selectTermAjax")
	public @ResponseBody Map<String, String> selectTermAjax(String term_id) {

		Map<String, String>  map =new  HashMap<String, String>();
		map.put("term_id", term_id);
		return map; 
		
	}

	@RequestMapping(value="viewStudentScore")
	public String ViweStudentScore(Model map){
		List<Term> listTerms = OfficeService.getAllTerm();
		map.addAttribute("listTerms", listTerms);
		return "Score/singleStudentScore";
	}
	@RequestMapping("viewMyScoreByTerm")
	public @ResponseBody WarpForScoreOnce viewMyScoreByTerm(String term_id){
	String 	student_id = UserUtils.getUser().getId();
		WarpForScore warpForScore  = new WarpForScore();
		Map<String , Object> map =  new HashMap<String, Object>();
		map.put("student_id", student_id);
		map.put("term_id", term_id);
		List<Score> scores = OfficeService.getStudentScore(map);
		
		if (scores.size()==0) {
			
			return null;
			
		}
		
		Score s = scores.get(0);
		
		UtilScore utilScore = new UtilScore();
		
		utilScore.setWarpForScore(warpForScore);
		
		utilScore.setScore(s);
		
		List<WarpForScore> list = new ArrayList<WarpForScore>();
		
		list.add(utilScore.getWarpForScore());
		
		WarpForScoreOnce warpForScoreOnce = new WarpForScoreOnce("1", "3", "10",list);
		
		
		
		
		return warpForScoreOnce;
	}
	
	
	
	
	
	
	
	
	
	

}
