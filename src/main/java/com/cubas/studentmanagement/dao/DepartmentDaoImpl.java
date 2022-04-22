package com.cubas.studentmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubas.studentmanagement.entity.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Department> getDepartments() {
		
//		System.out.println(">>>>IN DepartmentDaoImpl.getDepartments()");

		TypedQuery<Department> theQuery = entityManager.createQuery(
													"FROM Department",
													Department.class);
		
		List<Department> departments = theQuery.getResultList();
		
//		System.out.println(">>>>department list = " + departments);

		return departments;
	}

	@Override
	public Department getDepartmentById(Long id) {
		
		Department department = null;
		
		try {
			department = entityManager.find(
										Department.class, 
										id);				
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return department;
	}

	@Override
	public Department getDepartmentByName(String departmentName) {

		TypedQuery<Department> theQuery = entityManager.createQuery(
													"FROM Department "
											      + "WHERE name=:departmentName",
											      Department.class);
	
		theQuery.setParameter("departmentName", departmentName);
	
		Department department = theQuery.getSingleResult();
		
		return department;
	}

}
