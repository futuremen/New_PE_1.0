package com.hist.pe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.hist.serviceinter.StudentService;
import com.hist.pe.dao.StudentDao;
import com.hist.pe.entity.Student;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public Student getUserBySNumAndName(Long studentNum, String name) {
		System.out.println("studentNum : " + studentNum + ",name : " + name);
		return studentDao.getUserBySNumAndName(studentNum,name);
	}

	@Override
	public Student get(String id) {
		// TODO Auto-generated method stub
		return studentDao.get(id);
	}


	
}
