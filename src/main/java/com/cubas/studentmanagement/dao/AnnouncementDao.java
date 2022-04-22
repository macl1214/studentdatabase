package com.cubas.studentmanagement.dao;

import java.util.List;

import com.cubas.studentmanagement.entity.Announcement;

public interface AnnouncementDao {

	List<Announcement> getAnnouncements();

	void save(Announcement announcement);

	Announcement findById(Long id);
}
