package com.cubas.studentmanagement.dao;

import java.util.List;

import com.cubas.studentmanagement.entity.Role;

public interface RoleDao {

	List<Role> getRoles();
	
	Role getRole(Long id);
	
	Role getRole(String roleName);
}
