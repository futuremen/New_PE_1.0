package com.hist.pe.dao;

import java.util.List;
import java.util.Map;

import com.hist.pe.dao.base.Curd;
import com.hist.pe.entity.Teacher;

public interface TeacherDao extends Curd<Teacher>{
	
   List<Teacher>  getInstituteTeacherByPage(Map<String, Object> parameter);
   
   List<Teacher> getTeachers(Map<String, Object> parameter);

void updatePwd(Teacher teacher);

}
