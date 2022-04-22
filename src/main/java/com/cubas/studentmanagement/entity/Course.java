package com.cubas.studentmanagement.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="course_name")
	private String courseName;
	
	@Column(name="course_number")
	private Long courseNumber;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						  CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="department_id")
	private Department department;
	
	@OneToMany(mappedBy="course",
			   cascade = CascadeType.ALL)
	private List<Section> sections;
	
	public Course() {
		
	}

	public Course(Long id, String courseName, Long courseNumber, String description, Department department,
			List<Section> sections) {
		this.id = id;
		this.courseName = courseName;
		this.courseNumber = courseNumber;
		this.description = description;
		this.department = department;
		this.sections = sections;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public Long getCourseNumber() {
		return courseNumber;
	}
	
	public void setCourseNumber(Long courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", courseNumber=" + courseNumber + ", description="
				+ description + ", department=" + department.getName() + ", sections=" + sections + "]";
	}
	
}
