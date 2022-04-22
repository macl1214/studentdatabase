package com.cubas.studentmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubas.studentmanagement.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<User> getUsers() {
		TypedQuery<User> theQuery = 
				entityManager.createQuery("from User", User.class);
		
		List<User> users = theQuery.getResultList();
		
		return users;
	}

	@Override
	public User getUser(Long id) {
		
		User user = entityManager.find(User.class, id);
		
		return user;
	}
	
	@Override
	public List<User> getNewUsers() {
		
		TypedQuery<User> theQuery =
				entityManager.createQuery("from User "
										+ "where enabled=0",
										User.class);
		
		List<User> newUsers = theQuery.getResultList();
		
		return newUsers;
	}

	@Override
	public void deleteById(Long id) {

		Query theQuery = entityManager.createQuery(
									"delete from User "
								  + "where id=:userId");
		
		theQuery.setParameter("userId", id);
		
		theQuery.executeUpdate();
	}

	@Override
	public void save(User user) {

//		Save/Update the staff member
		User userDb = entityManager.merge(user);
		
//		Update the id from db, so we can get generated id for save/insert
//		Useful in REST API to return generated id
		user.setId(userDb.getId());
	}

	@Override
	public User findByUsername(String username) {
		
//		System.out.println(">>> In findByUsername\n"
//				+ "Looking for user that goes by the username '" + username +"'");
		
//		TypedQuery<SchoolMember> theQuery = 
//				entityManager.createQuery("from SchoolMember", SchoolMember.class);
//		
//		List<SchoolMember> staff = theQuery.getResultList();
//
//		System.out.println(staff);
		
		Query theQuery = entityManager.createQuery(
									"FROM User "
								    + "WHERE username=:uName");
		
		theQuery.setParameter("uName", username);
		
		User user = null;
		
		try {
			user = (User) theQuery.getSingleResult();
//			System.out.println("found user: " + user);
		} catch(Exception e) {
			
			System.out.println("user with username: " + username + " not found");
			user = null;
		}
		
		return user;
	}

}
