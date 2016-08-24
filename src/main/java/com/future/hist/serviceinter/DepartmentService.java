package com.future.hist.serviceinter;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hist.pe.entity.Department;
@Repository
public interface DepartmentService {

	public List<Department> getAllDp();
}
