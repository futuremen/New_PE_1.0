package com.hist.pe.controller;

import java.util.List;

import org.apache.poi.ss.usermodel.Footer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hist.pe.entity.Teacher;
import com.hist.pe.service.OfficeService;

@Controller
public class TeacherController {

	@RequestMapping(value = "insertTeacher")
	public String insertteacher(ModelMap modelMap,String teacher_id) {
		
		
		
		Teacher teacher = OfficeService.getOneTeacher(teacher_id);
		System.out.println(teacher_id);
		
		modelMap.addAttribute("teacher_id", teacher_id);
		return "teacher/insertTeacher";
	}

	@RequestMapping(value = "manageTeacher")
	public String manageTeacher() {
		
	
		return "teacher/manageTeacher";
	}
	
	
	
	@RequestMapping(value = "deleteTeacher")
	public String manageTeacher(String teacher_id) {
		
		OfficeService.deleteTeacher(teacher_id);
		
		
		return "redirect:manageTeacher";
	}
	
	
	@RequestMapping(value = "updateTeacher")
	public String updateTeacher(ModelMap modelMap,String teacher_id) {
		
	    modelMap.addAttribute("teacher_id", teacher_id);
		
		
		return "teacher/insertTeacher";
	}
	
	
	
	
	

	@RequestMapping(value = "insertTeacher", method = RequestMethod.POST)
	public String insertteacherPost(Teacher teacher) {

	
		teacher.setPassword("02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032");
		
		teacher.setRoleids("2,");
		
		if (teacher.getId().equals("")) {
			OfficeService.insertTeacher(teacher);
		} else {
			OfficeService.updateTeacher(teacher);
		}

		return "redirect:manageTeacher";
	}

}



