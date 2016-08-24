package com.hist.pe.dao;

import java.util.List;
import java.util.Map;

import com.hist.pe.dao.base.Curd;
import com.hist.pe.entity.Student;

public interface StudentDao extends Curd<Student>{
  
	
	
	       List<Student> getClassStudentByPage(Map<String, Object> map);
	       
	       Student getStudentByScoreId(String score_id);
	       
	       List<Student> getStudentByOrder(Map<String, Object> map);

		Student getUserBySNumAndName(Long studentNum, String name);

		void updateStudentPwd(Student student);

		void updateStudentForOrder(String student_id);
	       
	       
	       
	       
	       
	       
}
