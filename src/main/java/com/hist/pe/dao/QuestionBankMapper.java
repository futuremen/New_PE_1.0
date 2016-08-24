package com.hist.pe.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hist.pe.entity.QuestionsBank;
import com.hist.pe.entity.Type;

@Repository
public interface QuestionBankMapper {
	//得到所有科目
	public List<QuestionsBank> getAllQB();
	
	//查询指定科目是否已经存在
	public QuestionsBank findBank(String name);
	
	//添加科目
	public void addBank(QuestionsBank questionsBank);
	
	//删除科目
	public void deleteSubject(Integer bankId);
	
	//通过id查到指定的科目
	public QuestionsBank findById(Integer bankId);
	//更新科目
	public void updateBank(QuestionsBank questionsBank);
}
