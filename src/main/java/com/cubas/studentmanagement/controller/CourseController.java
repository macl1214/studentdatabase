package com.cubas.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cubas.studentmanagement.dto.CourseDto;
import com.cubas.studentmanagement.dto.SectionDto;
import com.cubas.studentmanagement.entity.Course;
import com.cubas.studentmanagement.entity.Department;
import com.cubas.studentmanagement.entity.User;
import com.cubas.studentmanagement.service.CourseService;
import com.cubas.studentmanagement.service.DepartmentService;
import com.cubas.studentmanagement.service.UserService;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/list")
	public String getCourses(Model model) {
		
//		List<User> staff = userService.getUser();
		List<CourseDto> courses = courseService.getCourseDtos();
		
		model.addAttribute("courses", courses);
		
		return "course/courses-list";
	}
	
	@GetMapping("/description")
	public String getCourse(@RequestParam("courseId") Long id,
							Model model) {
		
//		System.out.println("id passed = " + id);
		
		Course course = userService.findCourseById(id);
		List<SectionDto> sections = courseService.getSectionDtosFromClassId(id);
		
//		System.out.println("Course sections = ");
//		System.out.println(course.getSections());
		
		model.addAttribute("course", course);
		model.addAttribute("sections", sections);
		
		return "course/course-home";
	}
	
	@GetMapping("/addCourse")
	public String addCourse(Model model) {
		
		List<Department> departments = departmentService.getDeparments();
		CourseDto courseDto = new CourseDto();
		
		model.addAttribute("departments", departments);
		model.addAttribute("courseDto", courseDto);
		
		return "course/course-form";
	}
	
	@GetMapping("/updateCourse")
	public String updateCourse(@RequestParam("courseId") Long courseId,
							   Model model) {
		
		List<Department> departments = departmentService.getDeparments();
		CourseDto courseDto = courseService.getCourseDtoById(courseId);
		
		if (courseDto != null) {
			model.addAttribute("courseDto", courseDto);
			model.addAttribute("departments", departments);
			
			return "course/course-form";
		}
		
		return "course/list";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("courseDto") CourseDto courseDto) {
		
//		System.out.println(">>>> In course/save");
//		System.out.println("courseDto = " + courseDto);

		String departmentName = courseDto.getDepartmentName();
		Department department = departmentService.getDepartmentByName(departmentName);
		
		courseDto.setDepartment(department);
		
		
		courseDto = courseService.save(courseDto);
		
		return "redirect:/course/description?courseId=" + courseDto.getId();
	}
	
	@PostMapping("/deleteCourse")
	public String delete(@RequestParam("courseId") Long courseId) {
		
		
		
		return "redirect:/course/list";
	}
	
	@GetMapping("/addSection")
	public String addSection(@RequestParam("courseId") Long courseId,
							 Model model) {
		
		CourseDto courseDto = courseService.getCourseDtoById(courseId);
		
		if (courseDto == null) {
			
//			Pass Error Object later
			System.out.println("Course with id: " + courseId + " does not exist");
			
			return "course/list";
		}
		
		String departmentName = courseDto.getDepartmentName();
//		System.out.println("Course belongs to department = " + departmentName);
		 
		List<User> instructors = userService.getStaffByDepartmentName(departmentName);
//		System.out.println("Instuctors in that department:\n" + instructors);
		
		SectionDto sectionDto = new SectionDto();
		
		model.addAttribute("course", courseDto);
		model.addAttribute("section", sectionDto);
		model.addAttribute("instructors", instructors);
		
		return "course/section-form";
	}
	
	@PostMapping("/saveSection")
	public String saveSection(@ModelAttribute("section") SectionDto sectionDto) {
		
//		Set meetingDays
		sectionDto.setMeetingDays();
		
//		Set instructor
		Long instructorId = sectionDto.getInstructor().getId();
		User instructor = userService.findUserById(instructorId);
		sectionDto.setInstructor(instructor);
		
//		Set Course
		Long courseId = sectionDto.getCourse().getId();
		CourseDto courseDto = courseService.getCourseDtoById(courseId);
		sectionDto.setCourse(courseDto.convertToCourse());
		
//		Set SectionId
		Long sectionId = courseService.getNewSectionId();
		sectionDto.setSectionId(sectionId);
		
		System.out.println("sectionDto = " + sectionDto);
		
		courseService.save(sectionDto);
		
		return "redirect:/course/description?courseId=" + sectionDto.getCourse().getId();
	}
}
