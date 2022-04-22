package com.cubas.studentmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubas.studentmanagement.entity.Announcement;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao {
	
	@Autowired 
	private EntityManager entityManager;

	@Override
	public List<Announcement> getAnnouncements() {
		
		TypedQuery<Announcement> theQuery = 
				entityManager.createQuery("from Announcement", Announcement.class);
		
		List<Announcement> announcements = theQuery.getResultList();
		
		return announcements;
	}

	@Override
	public void save(Announcement announcement) {

		Announcement announcementDB = entityManager.merge(announcement);
		
		announcement.setId(announcementDB.getId());
	}

	@Override
	public Announcement findById(Long id) {
		
		Announcement announcementDB = entityManager.find(Announcement.class, id);
		
		return announcementDB;
	}

}
