package com.hist.pe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.hist.serviceinter.ScoreService;
import com.hist.pe.dao.ScoreMapper;
import com.hist.pe.entity.Page_1;
import com.hist.pe.entity.Score_1;

@Service
public class ScoreServiceImpl implements ScoreService{

	@Autowired
	private ScoreMapper scoreMapper;
	
	@Override
	public int save(Score_1 score) {
		return scoreMapper.save(score);
	}

	@Override
	public List<Score_1> getScoreByUserId(Long id) {
		System.out.println("user_id : "  + id);
		return scoreMapper.getScoreByUserId(id);
	}

	@Override
	public List<Score_1> findAllScoreByExamPageId(Long id) {
		System.out.println("ExamPage_id : " + id);
		return scoreMapper.findAllScoreByExamPageId(id);
	}

	@Override
	public Integer getCount() {
		return scoreMapper.getCount();
	}

	@Override
	public List<Score_1> getList(Page_1 page) {
		return scoreMapper.getList(page);
	}

}
