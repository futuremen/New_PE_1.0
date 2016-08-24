package com.hist.pe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hist.pe.dao.QuestionMapper;
import com.hist.pe.entity.PageBean;
import com.hist.pe.entity.Question;

@RestController
public class AjaxController {
	@Autowired
	private QuestionMapper qm;
	
	private int pageSize=5;
	// 得到所选的题
	@RequestMapping(value = "/hand_make_test", method = RequestMethod.POST)
	public Map<String, Object> getQuestion(@RequestParam("subject") int subject, @RequestParam("qtype") int qtype,@RequestParam("currentPage") int currentPage,@RequestParam("department")int department_id) {
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("subject", subject);
		map1.put("qtype", qtype);
		map1.put("department_id", department_id);
		//得到总的题数
		List<Question> questions = qm.getQuestionBy(map1);
		for (Question question:questions) {
			question.setIssue(question.getIssue().replaceAll("曱", ","));
		}
		System.out.println(questions);
		int recordCount=questions.size();
		if (recordCount!=0) {
			PageBean page=new PageBean(currentPage, pageSize, recordCount,questions);
			questions=page.getNeedlist();
			map1.clear();
			map1.put("questions", questions);
			map1.put("page", page);
		}
		
		return map1;
	}
	
	
}
