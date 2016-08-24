package com.future.hist.serviceinter;

import java.util.List;

import com.hist.pe.entity.ExamPage;

/**
 * 
 * @author wangshigen
 *
 */

public interface ExamineService {

	public List<ExamPage> getExamPages();

	public List<ExamPage> getQuestionsByExamPageId();

	public ExamPage getExamPageById(Long id);
	
	public void deleteExamPage(int id);
}
