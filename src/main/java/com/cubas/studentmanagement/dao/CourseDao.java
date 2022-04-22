package com.cubas.studentmanagement.dao;

import java.util.List;

import com.cubas.studentmanagement.entity.Course;

public interface CourseDao {
	
	List<Course> getCourses();

	List<Course> getCoursesByDepartmentId(Long dId);
	
	Course getCourseById(Long courseId);

	Course save(Course course);

	void deleteCourseById(Long courseId);
}
