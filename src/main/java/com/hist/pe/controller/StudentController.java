package com.hist.pe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hist.pe.entity.Student;
import com.hist.pe.entity.StudentClass;
import com.hist.pe.service.OfficeService;
import com.hist.pe.utils.UserUtils;

@Controller
public class StudentController {

	@RequestMapping(value = "insertStudent")
	public String insertScore(ModelMap modelMap, Student student) {

		return "Student/insertStudent";
	}

	@RequestMapping(value = "insertStudent", method = RequestMethod.POST)
	public String insertScorePost(Student student) {
		
		student.setPassword("02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032");
		student.setRole_id("1,");
		OfficeService.insertStudent(student);

		return "redirect:manageStudent";
	}
	
	@RequestMapping(value = "deleteStudent")
	public String insertScorePost(String student_id) {

		OfficeService.deleteStudent(student_id);
		return "redirect:manageStudent";
	}
	
	
	
	

	@RequestMapping(value = "manageStudent")
	public String manageStudent(ModelMap modelMap) {

		modelMap.addAttribute("classes", OfficeService.getAllClass());

		return "Student/manageStudent";
	}

	@RequestMapping(value = "StudentDetil")
	public String StudentDetil(ModelMap modelMap, String class_id) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("studentClass_id", class_id);

		List<Student> students = OfficeService.getClassStudent(map);

		modelMap.addAttribute("students", students);

		return "Student/StudentDetil";
	}

	@RequestMapping(value = "updateStudent")
	public String updateStudent(ModelMap modelMap, String student_id) {

		modelMap.addAttribute("student_id", student_id);

		return "Student/updateStudent";
	}

	@RequestMapping(value = "updateStudent", method = RequestMethod.POST)
	public String updateStudentPost(Student student) {

		OfficeService.updateStudent(student);

		return "redirect:manageStudent";
	}
	
	
	
	
	
	@RequestMapping(value = "viewClassStudent")
	public String viewClassStudent(ModelMap modelMap,String  class_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("studentClass_id", class_id);
		

		modelMap.addAttribute("students", OfficeService.getClassStudent(map));
		
		modelMap.addAttribute("studentClass_id", class_id);

		return "Score/classItemScore2";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
