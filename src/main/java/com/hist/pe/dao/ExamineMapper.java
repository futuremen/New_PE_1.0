package com.hist.pe.dao;

import java.util.List;

import com.hist.pe.entity.ExamPage;
import com.hist.pe.entity.PageBean;

public interface ExamineMapper {
	//
	List<ExamPage> getAllExampages();
	//添加试卷
	void addExamPage(ExamPage exampage);
	
	ExamPage getExamPageById(Long id);
	
	//分页查询试卷
	List<ExamPage> getExamPageByPage(PageBean pb);
	
	//删除试卷
	void deleteExamPageQE(int id);
	void deleteExamPageResult(int id);
	void deleteSocre(int id);
	void deleteExampage(int id);
}
