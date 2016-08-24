package com.hist.pe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hist.pe.entity.Term;
import com.hist.pe.service.OfficeService;
@Controller
public class TermController {
	
	@RequestMapping(value = "insertTerm")
	public String insertTerm() {
		
	
		return "term/insertTerm";
	}
	
	@RequestMapping(value = "insertTerm",method= RequestMethod.POST)
	public String insertTermPost(Term term) {
		
		
		if (term.getFlag()!=null) {
			OfficeService.insertTerm(term);
			OfficeService.cancelCurrentTerm();
			OfficeService.setCurrentTerm(term.getId());
		
		}else{
			
			OfficeService.insertTerm(term);
			
		}
			
		
		
		
		return "redirect:manageTerm";
	}
	
	
	@RequestMapping(value = "updateTerm")
	public String updateTerm(ModelMap modelMap,String term_id) {
		
		modelMap.put("term_id", term_id);
		
		return "term/updateTerm";
		
	}
	
	@RequestMapping(value = "updateTerm",method=RequestMethod.POST)
	public String updateTermPost(Term term) {
		
		if (term.getFlag()!=null) {
			OfficeService.cancelCurrentTerm();
			OfficeService.setCurrentTerm(term.getId());
			OfficeService.updateTerm(term);
		}else{
			
			OfficeService.updateTerm(term);
			
		}
		
		return "redirect:manageTerm";
		
	}
	
	@RequestMapping(value = "deleteTerm")
	public String deleteTerm(String term_id) {
		
		OfficeService.deleteTerm(term_id);
		
		return "redirect:manageTerm";
	}
	
	@RequestMapping(value = "manageTerm")
	public String manageTerm() {
		
		return "term/manageTerm";
	}

}
