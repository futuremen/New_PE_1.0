package com.hist.pe.dao;

import java.util.List;
import java.util.Map;

import com.hist.pe.dao.base.Curd;
import com.hist.pe.entity.Order;

public interface OrderDao extends Curd<Order>{
	
	List<Order> getTeacherOrderInfoByPage(Map<String, Object> map);
	
	List<Order> getStudentOrderInfoByPage(Map<String, Object> map);
	
	List<Order> getAll();

	void updateOrderForStudent(String order_id);
	
	


}
