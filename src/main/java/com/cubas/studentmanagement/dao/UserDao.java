package com.cubas.studentmanagement.dao;

import java.util.List;

import com.cubas.studentmanagement.entity.User;

public interface UserDao {

	List<User> getUsers();
	
//	List<User> getStaff();
	
	User getUser(Long id);
	
	void deleteById(Long id);
	
	void save(User user);

	User findByUsername(String username);

	List<User> getNewUsers();

}
