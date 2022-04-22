package com.cubas.studentmanagement.service;

import java.util.List;

import com.cubas.studentmanagement.dto.AnnouncementDto;

public interface AnnouncementService {

	List<AnnouncementDto> getAnnouncements();

	void save(AnnouncementDto announcementDTO);

	AnnouncementDto findById(Long id);
}
