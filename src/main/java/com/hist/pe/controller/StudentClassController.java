package com.hist.pe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hist.pe.entity.StudentClass;
import com.hist.pe.service.OfficeService;
@Controller
public class StudentClassController {
	
	
	
	@RequestMapping(value="manageStudentClass")
	public String manageInstitute() {	
		
		return "studentClass/manageStudentClass";
	}
	
	@RequestMapping(value="insertStudentClass")
	public String insertStudentClass() {	
			
		return "studentClass/insertStudentClass";
	}
	
	@RequestMapping(value="insertStudentClass",method = RequestMethod.POST)
	public String insertStudentClassPost(StudentClass class1,String teacher_id) {	
		
		OfficeService.insertStudentClass(class1);
		
		String class_id = class1.getId();
		
		OfficeService.insertUnionTable(teacher_id,class_id );
			
		return "redirect:manageStudentClass";
	}
	
	@RequestMapping(value="deleteStudentClass")
	public String deleteStudentClass(String studentClass_id) {	
		
		String dd = studentClass_id;
		
		OfficeService.deleteStudentClass(studentClass_id);
		OfficeService.deleteUnionTable(studentClass_id);
			
		return "redirect:manageStudentClass";
	}
	
	@RequestMapping(value="updateStudentClass")
	public String updateStudentClass(ModelMap map,String studentClass_id) {	
		
		map.addAttribute("studentClass_id", studentClass_id);
			
		return "studentClass/updateStudentClass";
	}
	
	
	@RequestMapping(value="updateStudentClass",method = RequestMethod.POST)
	public String updateStudentClassPost(StudentClass studentClass,String teacher_id) {	
		
		
		
		OfficeService.updateStudentClass(studentClass,teacher_id);
			
		return "redirect:manageStudentClass";
	}
	
	
	
	@RequestMapping(value="helloClass")
	public @ResponseBody StudentClass helloClass(String class_id) {	
		
		
		
		return OfficeService.getStudentClassItem(class_id);
			
		
	}


}
