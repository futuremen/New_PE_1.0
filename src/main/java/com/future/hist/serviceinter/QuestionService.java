package com.future.hist.serviceinter;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hist.pe.entity.Question;

@Transactional
public interface QuestionService {

	List<Question> getQuestionsByExamPageId(Long id);

}
