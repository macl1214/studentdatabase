package com.cubas.studentmanagement.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cubas.studentmanagement.entity.Course;
import com.cubas.studentmanagement.entity.Department;
import com.cubas.studentmanagement.entity.Section;

public class CourseDto {

	private Long id;
	
	@NotNull
	@Size(min = 5, max = 100, message = "is required")
	private String courseName;
	
	@NotNull
	private Long courseNumber;
	
	@NotNull
	@Size(min = 5, message = "is required")
	private String description;
	
	@NotNull
	private Department department;
	
	@NotNull
	private List<Section> sections;
	
//	Used for form input
	private String departmentName;
	
	public CourseDto() {
		
	}

	public CourseDto(Long id, @NotNull @Size(min = 5, max = 100, message = "is required") String courseName,
			@NotNull Long courseNumber, @NotNull @Size(min = 5, message = "is required") String description,
			@NotNull Department department, @NotNull List<Section> sections) {
		this.id = id;
		this.courseName = courseName;
		this.courseNumber = courseNumber;
		this.description = description;
		this.department = department;
		this.sections = sections;
		this.setDepartmentName(department.getName());
	}
	
	public CourseDto(Course course) {

		this(course.getId(),
			 course.getCourseName(),
			 course.getCourseNumber(),
			 course.getDescription(),
			 course.getDepartment(),
			 course.getSections());
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
		this.setDepartmentName(department.getName());
	}

	public List<Section> getSections() {
		return sections;
	}
	
	public int getNumberOfSections() {
		return sections.size();
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = this.toCapitalizedCase(departmentName);
	}
	
	private String toCapitalizedCase(String s) {
		
		if (s == null) {
			return null;
		}
		
		StringBuilder formattedString = new StringBuilder();
		
		formattedString.append(s.substring(0, 1).toUpperCase());
		formattedString.append(s.substring(1).toLowerCase());		
		
		return formattedString.toString();
	}
	
	public Course convertToCourse() {
	
		Course course = new Course(this.id,
								   this.courseName,
								   this.courseNumber,
								   this.description,
								   this.department,
								   this.sections);
		
		return course;
	}

	@Override
	public String toString() {
		return "CourseDto [id=" + id + ", courseName=" + courseName + ", courseNumber=" + courseNumber
				+ ", description=" + description + ", department=" + department + ", sections=" + sections
				+ ", departmentName=" + departmentName + "]";
	}
}
