package com.hist.pe.dao;

import java.util.List;
import java.util.Map;

import com.hist.pe.dao.base.Curd;
import com.hist.pe.entity.Institute;

public interface InstituteDao extends Curd<Institute>{
	
	List<Institute> getAllInstituteByPage(Map<String, Object> map);

}
