package com.hist.pe.dao;

import java.util.List;
import java.util.Map;

import com.hist.pe.dao.base.Curd;
import com.hist.pe.entity.StudentClass;

public interface StudentClassDao extends Curd<StudentClass> {
	
             List<StudentClass> getInstituteClassByPage(Map<String, Object> map);
             
             List<StudentClass> getTeacherClsaaByPage(Map<String, Object> map);
             
             List<StudentClass> getAllClass();
             
             void deleteUnionTable(String class_id);
             
             void insertUnionTable(Map<String, Object> map);
             
             void updateUnionTable(Map<String, Object> map);
             
             
             
             

}
