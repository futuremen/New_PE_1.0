package com.hist.pe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hist.pe.entity.Institute;
import com.hist.pe.service.OfficeService;

@Controller
public class InstituteController {
	
	@RequestMapping(value="manageInstitute")
	public String manageInstitute(ModelMap modelMap) {	
		return "institute/manageInstitute";
	}
	
	@RequestMapping(value="deleteInstitute")
	public String deleteInstitute(String institute_id) {		
		
		OfficeService.deleteInstitute(institute_id);
		
		return "redirect:manageInstitute";
	}
	
	@RequestMapping(value="insertInstitute")
	public String insertInstitute() {		
		
		return "institute/insertInstitute";
	}
	
	@RequestMapping(value="updateInstitute")
	public String updateInstitute(ModelMap map,String institute_id) {		
		map.addAttribute("institute_id", institute_id);
		return "institute/updateInstitute";
	}
	
	@RequestMapping(value="updateInstitute",method = RequestMethod.POST)
	public String updateInstitutePost(Institute institute) {		
		OfficeService.updateInstitute(institute);
		return "redirect:manageInstitute";
	}

	@RequestMapping(value="insertInstitute", method = RequestMethod.POST)
	public String insertInstitutePost(Institute institute) {	
		
		System.out.println(institute.getInstituteRemarks());
		OfficeService.insertInstitute(institute);
		return "redirect:manageInstitute";
	}
    
    
    
    
    
}
