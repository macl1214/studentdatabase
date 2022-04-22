package com.cubas.studentmanagement.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cubas.studentmanagement.config.SchoolUserDetails;
import com.cubas.studentmanagement.entity.Role;
import com.cubas.studentmanagement.entity.User;
import com.cubas.studentmanagement.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String login() {
		
		return "authorization/login";
	}
	
	@GetMapping("/register") 
	public String register(Model model,
						   @AuthenticationPrincipal SchoolUserDetails schoolUserDetails) {
		
		log.info("Registration Page");
		
//		Only allow users who are not looged in to register
		if (schoolUserDetails != null) {
			return "redirect:/";
		}
		
		User user = new User();
		Role role = new Role();
		
		model.addAttribute("user", user);
		model.addAttribute("role", role);
		
		return "authorization/register";
	}


	@PostMapping("/saveUser") 
	public String saveUser(@Valid @ModelAttribute("user") User user, 
						   BindingResult result,	
						   @ModelAttribute("role") Role tempRole) {
		
		log.info("Attempting to save user");		
		
		if (result.hasErrors()) {
			log.info("Found errors in the result");
			return "authorization/register"; 
		}
		
		Collection<Role> roles = new ArrayList<>();
		
		Role userRole = userService.getRole(tempRole.getName());
		roles.add(userRole);
		user.setRoles(roles);
		
//		On successful account creation, add a banner to the dashboard page detailing the login info:
//			-Username that is created (using first initial and lastname + number
//			-Will need to wait until an admin approves the account registration
//			-They will receive an email when the account is approved
		
		userService.save(user);
		
		return "redirect:/";
	}
} 
