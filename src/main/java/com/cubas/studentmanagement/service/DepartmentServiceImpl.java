package com.cubas.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubas.studentmanagement.dao.DepartmentDao;
import com.cubas.studentmanagement.entity.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	@Transactional
	public List<Department> getDeparments() {

		return departmentDao.getDepartments();
	}

	@Override
	@Transactional
	public Department getDepartmentById(Long id) {

		return departmentDao.getDepartmentById(id);
	}

	@Override
	@Transactional
	public Department getDepartmentByName(String name) {
		
		return departmentDao.getDepartmentByName(name);
	}

}
