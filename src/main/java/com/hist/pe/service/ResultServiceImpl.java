package com.hist.pe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.future.hist.serviceinter.ResultService;
import com.hist.pe.dao.ExamineMapper;
import com.hist.pe.dao.ResultMapper;
import com.hist.pe.entity.ExamPage;
import com.hist.pe.entity.Result;

@Service
@Transactional
public class ResultServiceImpl implements ResultService{

	@Autowired
	private ExamineMapper examineMapper; 
	
	@Autowired
	private ResultMapper resultMapper;
	
	@Override
	public List<ExamPage> getAllExampages() {
		
		return examineMapper.getAllExampages();
	}

	@Override
	public void save(Result result) {
		resultMapper.save(result);
	}
}
