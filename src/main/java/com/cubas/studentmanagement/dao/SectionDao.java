package com.cubas.studentmanagement.dao;

import java.util.List;

import com.cubas.studentmanagement.entity.Section;

public interface SectionDao {

	List<Section> getSections();
	
	List<Section> getSectionsFromClassId(Long cId);

	List<Section> getSectionsFromInstructorId(Long iId);
	
	Section getSectionById(Long sectionId);
	
	void save(Section section);
	
	void deleteSectionById(Long sectionId);
}
