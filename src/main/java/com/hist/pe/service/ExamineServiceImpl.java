package com.hist.pe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.future.hist.serviceinter.ExamineService;
import com.hist.pe.dao.ExamineMapper;
import com.hist.pe.entity.ExamPage;

/**
 * 
 * @author wangshigen
 *
 */
@Service
@Transactional
public class ExamineServiceImpl implements ExamineService {

	@Autowired
	private ExamineMapper examineMapper;
	
	@Override
	public List<ExamPage> getExamPages() {
		
		return examineMapper.getAllExampages();
	}

	@Override
	public List<ExamPage> getQuestionsByExamPageId() {
		return null;
	}

	@Override
	public ExamPage getExamPageById(Long id) {
		return examineMapper.getExamPageById(id);
	}

	@Override
	public void deleteExamPage(int id) {
		examineMapper.deleteExamPageQE(id);
		examineMapper.deleteExamPageResult(id);
		examineMapper.deleteSocre(id);
		examineMapper.deleteExampage(id);
	}

}
