package com.cubas.studentmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubas.studentmanagement.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Role> getRoles() {
		
		TypedQuery<Role> theQuery = entityManager.createQuery(
													"From Role", 
													Role.class);
		
		List<Role> roles = theQuery.getResultList();
		
		return roles;
	}

	@Override
	public Role getRole(Long id) {
		
		Role role = null;
		
		try {
			role = entityManager.find(Role.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return role;
	}

	@Override
	public Role getRole(String roleName) {

		TypedQuery<Role> theQuery = entityManager.createQuery(
													"FROM Role "
												  + "WHERE name =: roleName",
												  Role.class);
		
		theQuery.setParameter("roleName", roleName);
		
		Role role = theQuery.getSingleResult();
		
		return role;
	}

}
