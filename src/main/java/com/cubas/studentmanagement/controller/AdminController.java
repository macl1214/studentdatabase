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

import com.cubas.studentmanagement.entity.Course;
import com.cubas.studentmanagement.entity.User;
import com.cubas.studentmanagement.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/newUsers")
	public String getNewUsersList(Model model) {
		
		List<User> newUsers = userService.getNewUsers();
		
		model.addAttribute("newUsers", newUsers);
		
		return "admin/new-users-list";
	}
	
	@GetMapping("/staff")
	public String getStaff(Model model) {
		
//		List<User> staff = userService.getUser();
		List<User> staff = userService.getStaff();
		
		model.addAttribute("staff", staff);
		
		return "admin/staff-list";
	}
	
	@GetMapping("/students")
	public String getStudents(Model model) {
		
//		List<User> staff = userService.getUser();
		List<User> students = userService.getStudents();
		
		model.addAttribute("students", students);
		
		return "admin/students-list";
	}
	
	@GetMapping("/courses")
	public String getCourses(Model model) {
		
//		List<User> staff = userService.getUser();
		List<Course> courses = userService.getCourses();
		
		model.addAttribute("courses", courses);
		
		return "course/courses-list";
	}
	
	@GetMapping("/addStaff")
	public String addStaff(Model model) {
		
		User staffMember = new User();
		
		model.addAttribute("staffMember", staffMember);
		
		return "admin/staff-form";
	}
	
	@GetMapping("/updateStaff")
	public String updateStaff(@RequestParam("staffId") Long id, Model model) {
		
		User staffMember = userService.findUserById(id);
		
		model.addAttribute("staffMember", staffMember);
		
		return "admin/staff-form";
	}

	@GetMapping("/deleteStaff")
	public String delete(@RequestParam("staffId") Long id, Model model) {
		
		userService.deleteById(id);
		
		return "redirect:/admin/staff";
	}
	
	@PostMapping("/save")
	public String saveStaff(@ModelAttribute("staffMember") User staffMember) {
		
		System.out.println(staffMember);
		userService.save(staffMember);
		
		
		return "redirect:/admin/staff";
	}
}
