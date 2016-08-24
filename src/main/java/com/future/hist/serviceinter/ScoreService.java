package com.future.hist.serviceinter;

import java.util.List;

import com.hist.pe.entity.Page_1;
import com.hist.pe.entity.Score_1;

public interface ScoreService {

	int save(Score_1 score);

	List<Score_1> getScoreByUserId(Long id);

	List<Score_1> findAllScoreByExamPageId(Long id);

	Integer getCount();

	List<Score_1> getList(Page_1 page);

}
