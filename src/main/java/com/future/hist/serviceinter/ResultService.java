package com.future.hist.serviceinter;

import java.util.List;

import com.hist.pe.entity.ExamPage;
import com.hist.pe.entity.Result;

public interface ResultService {

	List<ExamPage> getAllExampages();

	void save(Result result);

	
	
}
