package com.cubas.studentmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubas.studentmanagement.entity.Course;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Course> getCourses() {

		TypedQuery<Course> theQuery = entityManager.createQuery(
													"FROM Course", 
													Course.class);
		
		List<Course> courses = theQuery.getResultList();
		
		return courses;
	}
	
	@Override
	public List<Course> getCoursesByDepartmentId(Long dId) {

		TypedQuery<Course> theQuery = entityManager.createQuery(
													"FROM Course "
												  + "WHERE department_id =:dId",
												  Course.class);
		
		theQuery.setParameter("dId", dId);
		
		List<Course> courses = theQuery.getResultList();
		
		return courses;
	} 	
	
	@Override
	public Course getCourseById(Long id) {
		
		Course course = entityManager.find(Course.class, id);
		
		return course;
	}

	@Override
	public Course save(Course course) {

		Course courseDB = entityManager.merge(course);
		
		course.setId(courseDB.getId());
		
		return course;
	}

	@Override
	public void deleteCourseById(Long courseId) {
		// TODO Auto-generated method stub
		
	}
}
