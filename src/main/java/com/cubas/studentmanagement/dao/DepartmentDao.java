package com.cubas.studentmanagement.dao;

import java.util.List;

import com.cubas.studentmanagement.entity.Department;

public interface DepartmentDao {

	List<Department> getDepartments();
	
	Department getDepartmentById(Long id);
	
	Department getDepartmentByName(String departmentName);
}
