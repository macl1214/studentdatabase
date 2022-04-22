package com.cubas.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubas.studentmanagement.dao.CourseDao;
import com.cubas.studentmanagement.dao.SectionDao;
import com.cubas.studentmanagement.dto.CourseDto;
import com.cubas.studentmanagement.dto.SectionDto;
import com.cubas.studentmanagement.entity.Course;
import com.cubas.studentmanagement.entity.Section;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private SectionDao sectionDao;
	
	@Override
	@Transactional
	public List<CourseDto> getCourseDtos() {

		List<Course> coursesDB = courseDao.getCourses();
		List<CourseDto> coursesDto = null;
		
		if (coursesDB != null) {			
			coursesDto = convertCourseListToDtoList(coursesDB);
		}
		
		return coursesDto;
	}

	@Override
	@Transactional
	public List<CourseDto> getCourseDtosFromDepartmentId(Long dId) {
		
		List<Course> coursesDB = courseDao.getCoursesByDepartmentId(dId);
		List<CourseDto> coursesDto = null;

		if (coursesDB != null) {
			coursesDto = convertCourseListToDtoList(coursesDB);
		}
		
		return coursesDto;
	}

	@Override
	@Transactional
	public CourseDto getCourseDtoById(Long courseId) {

		Course courseDB = courseDao.getCourseById(courseId);

		CourseDto courseDto = null;
		
		if (courseDB != null) {
			courseDto = new CourseDto(courseDB);
		}
		
		return courseDto;
	}

	@Override
	@Transactional
	public CourseDto save(CourseDto courseDto) {

		if (courseDto != null) {
			Course course = courseDto.convertToCourse();
			
			CourseDto newCourseDto = new CourseDto(courseDao.save(course));
			
			return newCourseDto;
		}
		
		return null;
	}

	@Override
	@Transactional
	public void deleteCourseById(Long courseId) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	@Transactional
	public List<SectionDto> getSectionDtos() {
		List<Section> sectionsDB = sectionDao.getSections();
		List<SectionDto> sectionsDto = null;
		
		if (sectionsDB != null) {
			sectionsDto = convertSectionListToDtoList(sectionsDB);
		}
		
		return sectionsDto;
	}

	@Override
	@Transactional
	public List<SectionDto> getSectionDtosFromClassId(Long cId) {
		List<Section> sectionsDB = sectionDao.getSectionsFromClassId(cId);
		List<SectionDto> sectionsDto = null;
		
		if (sectionsDB != null) {			
			sectionsDto = convertSectionListToDtoList(sectionsDB);
		}
		
		return sectionsDto;
	}

	@Override
	@Transactional
	public List<SectionDto> getSectionDtosFromInstructorId(Long iId) {
		List<Section> sectionsDB = sectionDao.getSections();
		List<SectionDto> sectionsDto = null;
		
		if (sectionsDB != null) {			
			sectionsDto = convertSectionListToDtoList(sectionsDB);
		}
		
		return sectionsDto;
	}

	@Override
	@Transactional
	public SectionDto getSectionDtoById(Long sectionId) {
		Section sectionDB = sectionDao.getSectionById(sectionId);

		SectionDto sectionDto = null;
		
		if (sectionDB != null) {
			new SectionDto(sectionDB);
		}
		
		return sectionDto;
	}

	@Override
	@Transactional
	public void save(SectionDto sectionDto) {

		if (sectionDto != null) {
			
			Section section = sectionDto.convertToSection();
			
			sectionDao.save(section);
		}
	}

	@Override
	@Transactional
	public void deleteSectionDtoById(Long sectionId) {
		// TODO Auto-generated method stub

	}
	
	@Override
	@Transactional
	public Long getNewSectionId() {
		
		List<Section> sections = sectionDao.getSections();
		Long newId = null;
		
		Section curLastSection = sections.get(sections.size() - 1);
		
		newId = curLastSection.getSectionId() + 1;  
		
		return newId;
	}
	
	private List<CourseDto> convertCourseListToDtoList(List<Course> courses) {
		
		if (courses == null) {
			return null;
		}
		
		List<CourseDto> courseDtoList = new ArrayList<>();
		
		for (Course s : courses) {
			
			courseDtoList.add(new CourseDto(s));
		}
		
		return courseDtoList;
	}
	
	private List<SectionDto> convertSectionListToDtoList(List<Section> sections) {
		
		if (sections == null) {
			return null;
		}
		
		List<SectionDto> sectionDtoList = new ArrayList<>();
		
		for (Section s : sections) {
			
			sectionDtoList.add(new SectionDto(s));
		}
		
		return sectionDtoList;
	}
	
}