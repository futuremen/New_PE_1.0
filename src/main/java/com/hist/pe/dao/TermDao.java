package com.hist.pe.dao;

import java.util.List;

import com.hist.pe.dao.base.Curd;
import com.hist.pe.entity.Term;

public interface TermDao extends Curd<Term>{
	
	Term getCurrentTerm();
	
	List<Term> getAllTerm();
	
	void setCurrentTerm(String term_id);
	
	void cancelCurrentTerm();

}
