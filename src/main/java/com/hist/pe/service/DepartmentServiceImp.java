package com.hist.pe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.future.hist.serviceinter.DepartmentService;
import com.hist.pe.dao.DepartmentMapper;
import com.hist.pe.entity.Department;
@Repository
public class DepartmentServiceImp implements DepartmentService{

	@Autowired
	private DepartmentMapper dm;
	@Override
	public List<Department> getAllDp() {
		List<Department> list=dm.getAllDp();
		return list;
	}

}
