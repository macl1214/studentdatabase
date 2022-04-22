package com.cubas.studentmanagement.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cubas.studentmanagement.config.SchoolUserDetails;
import com.cubas.studentmanagement.service.CourseService;
import com.cubas.studentmanagement.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CourseService courseService;
	
	@GetMapping("/")
	public String getHome(Model model,
						  @AuthenticationPrincipal SchoolUserDetails schoolUserDetails) {
		
//		Check if user is logged in
		if (schoolUserDetails != null) {
			Collection<? extends GrantedAuthority> authorities = schoolUserDetails.getAuthorities();
			
			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals("ROLE_ADMIN")) {
					getAdminDashModel(model);
				}
				else if (authority.getAuthority().equals("ROLE_TEACHER")) {
					System.out.println("teacher");
				}
				else if (authority.getAuthority().equals("ROLE_STUDENT")) {
					System.out.println("student");
				}
			}
			
		}
		return "index";
	}
		
	@GetMapping("/static-nav") 
	public String staticNav() {
		
		return "layout-static";
	}
	
	@GetMapping("/charts") 
	public String charts() {
		
		return "charts";
	}
	
	@GetMapping("/tables") 
	public String tables() {
		
		return "tables";
	}
	
//	Helper Methods
	public void getAdminDashModel(Model model) {
		
		Integer teachersCount = userService.getStaff().size();
		Integer studentsCount = userService.getStudents().size();
		Integer coursesCount = courseService.getCourseDtos().size();
		
		model.addAttribute("teachersCount", teachersCount);
		model.addAttribute("studentsCount", studentsCount);
		model.addAttribute("coursesCount", coursesCount);
	}
}
