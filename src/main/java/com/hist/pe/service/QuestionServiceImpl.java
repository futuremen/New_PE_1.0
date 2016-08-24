package com.hist.pe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.hist.serviceinter.QuestionService;
import com.hist.pe.dao.QuestionMapper;
import com.hist.pe.entity.Question;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionMapper questionMapper;
	
	public List<Question> getQuestionsByExamPageId(Long id) {
		return questionMapper.getQuestionsByExamPageId(id);
	}

	
}
