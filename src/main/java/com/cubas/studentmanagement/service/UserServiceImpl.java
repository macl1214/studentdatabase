package com.cubas.studentmanagement.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubas.studentmanagement.config.SchoolUserDetails;
import com.cubas.studentmanagement.controller.LoginController;
import com.cubas.studentmanagement.dao.CourseDao;
import com.cubas.studentmanagement.dao.DepartmentDao;
import com.cubas.studentmanagement.dao.RoleDao;
import com.cubas.studentmanagement.dao.UserDao;
import com.cubas.studentmanagement.entity.Course;
import com.cubas.studentmanagement.entity.Department;
import com.cubas.studentmanagement.entity.Role;
import com.cubas.studentmanagement.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	@Transactional
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	@Override
	@Transactional
	public List<User> getUsers() {
		return userDao.getUsers();
	}
	
	@Override
	@Transactional
	public List<User> getStaff() {
		
		List<User> users = userDao.getUsers();
		List<User> staff = new ArrayList<>();
		Role studentRole = roleDao.getRole("ROLE_TEACHER");
		
		for (User u : users) {
//			System.out.println(u);
			
			if (u.getRoles().contains(studentRole)) {
				staff.add(u);
			}
		}
		
		return staff;
	}
	
	@Override
	@Transactional
	public List<User> getStaffByDepartmentName(String dName) {
		
		List<User> users = userDao.getUsers();
		List<User> staff = new ArrayList<>();
		Role staffRole = roleDao.getRole("ROLE_TEACHER");
		Department department = departmentDao.getDepartmentByName(dName);
		
		if (department != null) {
			
			for (User u : users) {
//				System.out.println(u);
				
				if (u.getRoles().contains(staffRole) && u.getInstructorDepartment().equals(department)) {
					staff.add(u);
				}
			}
			
			return staff;
		}

		System.out.println("Department: " + dName + " does not exist.");
		return null;
	}

	@Override
	@Transactional
	public List<User> getStudents() {
		
		List<User> users = userDao.getUsers();
		List<User> students = new ArrayList<>();
		Role studentRole = roleDao.getRole("ROLE_STUDENT");
		
		for (User u : users) {
//			System.out.println(u);
			
			if (u.getRoles().contains(studentRole)) {
				students.add(u);
			}
		}
		
		return students;
	}
	
	@Override
	@Transactional
	public List<User> getNewUsers() {
		
		List<User> newUsers = userDao.getNewUsers();
		
		return newUsers;
	}
	
	@Override
	@Transactional
	public List<Course> getCourses() {
		
		return courseDao.getCourses();		
	}

	@Override
	@Transactional
	public User findUserById(Long id) {

		return userDao.getUser(id);
	}

	@Override
	@Transactional
	public Course findCourseById(Long id) {

		return courseDao.getCourseById(id);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		userDao.deleteById(id);
	}

	@Override
	@Transactional
	public void save(User user) {

		log.info("Saving user...");
		
		System.out.println("User before adding role: " + user);
		
		Role baseRole = roleDao.getRole("ROLE_SCHOOL_MEMBER");
		
		Collection<Role> userRoles = user.getRoles();
		userRoles.add(baseRole);
		
		user.setEnabled(false);
		
		System.out.println("User after adding role: " + user);
		
//		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) 
							throws UsernameNotFoundException {
		
		User member = userDao.findByUsername(username);
		
		if (member == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
		return new SchoolUserDetails(member);
	}
	
	@Override
	@Transactional
	public Role getRole(String roleName) {
		
		return roleDao.getRole(roleName);
	}
}
