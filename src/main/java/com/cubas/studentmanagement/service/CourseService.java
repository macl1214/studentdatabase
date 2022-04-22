package com.cubas.studentmanagement.service;

import java.util.List;

import com.cubas.studentmanagement.dto.CourseDto;
import com.cubas.studentmanagement.dto.SectionDto;

public interface CourseService {

	/** Courses */
	List<CourseDto> getCourseDtos();

	List<CourseDto> getCourseDtosFromDepartmentId(Long dId);

	CourseDto getCourseDtoById(Long courseId);
	
	CourseDto save(CourseDto courseDto);
	
	void deleteCourseById(Long courseId);
	
	/** Sections */
	List<SectionDto> getSectionDtos();

	List<SectionDto> getSectionDtosFromClassId(Long cId);

	List<SectionDto> getSectionDtosFromInstructorId(Long iId);
	
	SectionDto getSectionDtoById(Long sectionId);
	
	void save(SectionDto sectionDto);
	
	void deleteSectionDtoById(Long sectionId);

	Long getNewSectionId();
}
