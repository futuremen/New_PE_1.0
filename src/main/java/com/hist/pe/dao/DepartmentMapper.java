package com.hist.pe.dao;

import java.util.List;

import com.hist.pe.entity.Department;

public interface DepartmentMapper {

	public List<Department> getAllDp();
	
	public void deleteDepartment(Integer departmentId);
	
	public void updateDepartment(Department department);
	
	public Department findById(Integer departmentId);
	
	public Department findByName(String departmentName);
	
	public void addDepartment(Department department);
}
