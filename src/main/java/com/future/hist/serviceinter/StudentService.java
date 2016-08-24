package com.future.hist.serviceinter;

import com.hist.pe.entity.Student;
import com.hist.pe.entity.User;

public interface StudentService {

	Student getUserBySNumAndName(Long studentNum, String name);

	Student get(String id);

}
