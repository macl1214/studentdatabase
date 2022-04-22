package com.cubas.studentmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubas.studentmanagement.entity.Section;

@Repository
public class SectionDaoImpl implements SectionDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Section> getSections() {

		TypedQuery<Section> theQuery = entityManager.createQuery(
													"FROM Section",
													Section.class);
		
		List<Section> sections = theQuery.getResultList();
		
		return sections;
	}

	@Override
	public List<Section> getSectionsFromClassId(Long cId) {

		TypedQuery<Section> theQuery = entityManager.createQuery(
													"FROM Section "
												  + "WHERE course_id =:cId",
													Section.class);
		
//		System.out.println("class id = " + cId);
		
		theQuery.setParameter("cId", cId);
		
		List<Section> sections = theQuery.getResultList();
		
//		System.out.println("Result set: " + sections);
		
		return sections;
	}

	@Override
	public List<Section> getSectionsFromInstructorId(Long iId) {
		TypedQuery<Section> theQuery = entityManager.createQuery(
													"FROM Section "
												  + "WHERE instructorId =:iId",
													Section.class);
		
		theQuery.setParameter("cId", iId);
		
		List<Section> sections = theQuery.getResultList();
		
		return sections;
	}

	@Override
	public Section getSectionById(Long sectionId) {
		Section section = entityManager.find(Section.class, sectionId);
		
		return section;
	}

	@Override
	public void save(Section section) {

		Section sectionDB = entityManager.merge(section);
		
		section.setId(sectionDB.getId());
	}

	@Override
	public void deleteSectionById(Long sectionId) {
		// TODO Auto-generated method stub

	}

}
