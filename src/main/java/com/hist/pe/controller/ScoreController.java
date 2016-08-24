//package com.hist.pe.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//import javax.websocket.Session;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.hist.pe.entity.Institute;
//import com.hist.pe.entity.Score;
//import com.hist.pe.entity.Student;
//import com.hist.pe.entity.StudentClass;
//import com.hist.pe.service.OfficeService;
//import com.hist.pe.utils.UserUtils;
//
//@Controller
//public class ScoreController {
//
//	@RequestMapping(value = "manageScore")
//	public String manageScore(ModelMap modelMap) {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		String teacher_id = UserUtils.getUser().getId();
//
//		map.put("teacher_id", teacher_id);
//
//		String class_id = OfficeService.getTeacherClsaaByPage(map).get(0).getId();
//
//		map.clear();
//
//		map.put("class_id", class_id);
//
//		modelMap.addAttribute("students", OfficeService.getClassStudent(map));
//
//		return "Score/manageScore";
//	}
//	
//	
//	@RequestMapping(value = "getScoreByTerm",method=RequestMethod.POST)
//	public String getScoreByTerm(ModelMap modelMap,String term_id,String class_id) {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		
//        map.put("studentClass_id", class_id);
//		
//        
//        List<Student> students = OfficeService.getClassStudent(map);
//
//		modelMap.addAttribute("students",students );
//		
//		map.put("term_id", term_id);
//		
//		List<Score> scores = OfficeService.getClassScore(map);
//		
//		modelMap.addAttribute("scores", OfficeService.getClassScore(map));
//		
//		modelMap.addAttribute("studentClass_id", class_id);
//
//
//		return "Score/classItemScore2";
//	}
//	
//	
//	
//	
//
//	@RequestMapping(value = "manageScore", method = RequestMethod.POST)
//	public String manageScorePost(ModelMap modelMap, String class_id) {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		map.put("studentClass_id", class_id);
//
//	 
//
//		modelMap.addAttribute("classItem",OfficeService.getStudentClassItem(class_id));
//
//		
//		return "Score/manageScore";
//	}
//
//	@RequestMapping(value = "insertScore")
//	public String insertScore(HttpSession session, ModelMap modelMap, String student_id) {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		map.put("student_id", student_id);
//
//		String term_id = (String) session.getAttribute("term_id");
//
//		map.put("term_id", term_id);
//
//		List<Score> scores = OfficeService.getOriginalStudentScore(map);
//		Score score = null;
//		if (scores.size() != 0) {
//			score = scores.get(0);
//		}
//		modelMap.addAttribute("score", score);
//
//		modelMap.addAttribute("student_id", student_id);
//
//		return "Score/insertScore";
//	}
//
//	@RequestMapping(value = "insertScore", method = RequestMethod.POST)
//	public String insertScorePost(ModelMap modelMap, Score score) {
//
//		String term_id = OfficeService.getCurrentTerm().getId();
//
//		score.setTerm_id(term_id);
//
//		if (score.getId().equals("")) {
//			OfficeService.insertScore(score);
//
//		} else {
//			OfficeService.updateScore(score);
//
//		}
//
//		OfficeService.getReallyScore();
//
//		return "redirect:manageScore";
//	}
//
//	@RequestMapping(value = "hello")
//	public @ResponseBody List<StudentClass> hello(String institute_id) {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		map.put("institute_id", institute_id);
//
//		List<StudentClass> x = OfficeService.getInstituteClass(map);
//
//		return x;
//
//	}
//
//	@RequestMapping(value = "viewStudentScore")
//	public String viewStudentScore(ModelMap modelMap) {
//
//		List<Score> list = new ArrayList<Score>();
//		String term_id = OfficeService.getCurrentTerm().getId();
//
//		if (UserUtils.getUser().getStudent() == null) {
//
//			Map<String, Object> map = new HashMap<String, Object>();
//
//			map.put("teacher_id", UserUtils.getUser().getId());
//			List<StudentClass> classes = OfficeService.getTeacherClsaaByPage(map);
//			StudentClass studentClass = classes.get(0);
//			map.clear();
//			map.put("class_id", studentClass.getId());
//			List<Student> students = OfficeService.getClassStudent(map);
//
//			Map<String, Object> map1 = new HashMap<String, Object>();
//
//			for (int i = 0; i < students.size(); i++) {
//				map1.put("student_id", students.get(i).getId());
//				map1.put("term_id", term_id);
//				list.add(OfficeService.getStudentScore(map).get(0));
//			}
//			modelMap.addAttribute("scores", list);
//
//		} else {
//
//			String student_id = UserUtils.getUser().getId();
//
//			Map<String, Object> map = new HashMap<String, Object>();
//
//			map.put("student_id", student_id);
//
//			map.put("term_id", term_id);
//
//			Score score = OfficeService.getStudentScore(map).get(0);
//
//			list.add(score);
//
//			modelMap.addAttribute("scores", list);
//			modelMap.addAttribute("term_id", term_id);
//		}
//		return "Score/viewStudentScore";
//	}
//
//}
