package com.cubas.studentmanagement.service;

import java.util.List;

import com.cubas.studentmanagement.entity.Department;

public interface DepartmentService {

	List<Department> getDeparments();
	
	Department getDepartmentById(Long id);
	
	Department getDepartmentByName(String name);
}
