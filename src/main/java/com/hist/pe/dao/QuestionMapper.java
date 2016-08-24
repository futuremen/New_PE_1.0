package com.hist.pe.dao;

import java.util.List;
import java.util.Map;

import com.hist.pe.entity.Degree;
import com.hist.pe.entity.FillQuestion;
import com.hist.pe.entity.JudgeQuestion;
import com.hist.pe.entity.PageBean;
import com.hist.pe.entity.Question;
import com.hist.pe.entity.SelectQuestion;

/**
 * 
 * @author wangshigen
 *
 */
public interface QuestionMapper {

	public List<Question> getQuestionsByExamPageId(Long examPage_id);

	public List<Question> getQuestion(Map<String, Integer> map1);
	
	//根据类型和科目得到问题
	public List<Question> getQuestionBy(Map<String,Object> map);
	
	//根据id得到问题
	public List<Question> getQuestionById(List<String> list);

	//分页得到的数据
	public List<Question> getQuestionsByPage(PageBean pb);
	
	//关联Question和exqmpage
	public void updateQuestion(Map<String,Object> map);
	
	//随机查询
	public List<Question> randFindQuestion(Map<String, Object> map);
	
	//=============================================
	//查找指定科目的试题 write by zhaoshuo
	//=============================================
	//查询是否还有某种科目的试题
	public Integer findByQuestionBank(Integer bankId);
	//查询是否还有某种题型的试题
	public Integer findByQuestionType(Integer typeId);
	//查询是否还有某个系别的试题
	public Integer findByDepartment(Integer departmentId);
	
	
	//添加选择试题
	public void addSelectOneQuestion(SelectQuestion sQuestion);
	
	//添加填空题
	public void addFillQuestion(FillQuestion fQuestion );
	
	//添加判断题
	public void addJudgeQuestion(JudgeQuestion jQuestion);
	
	//查找指定的题目
	public List<Question> getSpecialSelectOne(Map<String, Object> map);
	
	//删除题目
	public void deleteQuestion(Integer questionId);
	
	//查询是否有外键约束
	public Integer findQuestionForeignKey(Integer questionId);
	//去除外键约束
	public void deleteQuestionForeignKey(Integer questionId);
	
	//根据id得到制定试题
	public Question getQuestionBySpecil(Integer questionId);
	
	//更新指定的单选题目
	public void updateSpecialQuestion(Question question);
	//得到指定类型和系别的题目的数量
	public Integer getSpecialNum(Map<String, Object> map);
}
