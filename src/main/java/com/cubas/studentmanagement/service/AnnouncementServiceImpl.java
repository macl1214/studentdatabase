package com.cubas.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubas.studentmanagement.dao.AnnouncementDao;
import com.cubas.studentmanagement.dto.AnnouncementDto;
import com.cubas.studentmanagement.entity.Announcement;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
	
	@Autowired
	private AnnouncementDao announcementDao;

	@Override
	@Transactional
	public List<AnnouncementDto> getAnnouncements() {
		List<Announcement> announcementListDB = announcementDao.getAnnouncements();
		
		List<AnnouncementDto> announcementDTOList = null;
		
		if (announcementListDB != null) {

			announcementDTOList = new ArrayList<>();
			
			for (Announcement announcement : announcementListDB) {
				AnnouncementDto announcementDTO = new AnnouncementDto(
														announcement.getTitle(),
														announcement.getPost(),
														announcement.getTimestamp(),
														announcement.getUser());

				announcementDTO.setId(announcement.getId());
				
//				System.out.println(announcementDTO.getTimestamp());

				announcementDTOList.add(announcementDTO);
			}
		}
		
		return announcementDTOList;
	}
	
	@Override
	@Transactional
	public AnnouncementDto findById(Long id) {
		
		Announcement announcementDB = announcementDao.findById(id);
		
		AnnouncementDto announcement = null;
		
		if (announcementDB != null) {
			announcement = new AnnouncementDto(
					announcementDB.getTitle(),
					announcementDB.getPost(),
					announcementDB.getTimestamp(),
					announcementDB.getUser());
			
			announcement.setId(announcementDB.getId());
		}
		
		return announcement;
	}

	@Override
	@Transactional
	public void save(AnnouncementDto announcementDto) {
		
		Announcement announcement = announcementDto.convertToAnnouncement();
		
		announcementDao.save(announcement);
	}

}
