package com.cubas.studentmanagement.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cubas.studentmanagement.entity.Course;
import com.cubas.studentmanagement.entity.Role;
import com.cubas.studentmanagement.entity.User;

public interface UserService extends UserDetailsService {
	
	User findByUsername(String username);

	List<User> getUsers();

	List<User> getStaff();
	
	List<User> getStaffByDepartmentName(String dName);

	User findUserById(Long id);
	
	List<Course> getCourses();
	
	Course findCourseById(Long id);
		
	void deleteById(Long id);
	
	void save(User user);

	List<User> getStudents();

	Role getRole(String roleName);

	List<User> getNewUsers(); 
}
