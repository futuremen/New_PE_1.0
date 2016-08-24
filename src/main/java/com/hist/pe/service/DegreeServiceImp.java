package com.hist.pe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.future.hist.serviceinter.DegreeService;
import com.hist.pe.dao.DegreeMapper;
import com.hist.pe.entity.Degree;
@Repository
public class DegreeServiceImp implements DegreeService{
	@Autowired
	private DegreeMapper dm;
	@Override
	public List<Degree> getAllDegree() {
		List<Degree> list=dm.getAllDegree();
		return list;
	}

}
