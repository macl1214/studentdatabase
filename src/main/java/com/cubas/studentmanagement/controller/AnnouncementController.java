package com.cubas.studentmanagement.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cubas.studentmanagement.config.SchoolUserDetails;
import com.cubas.studentmanagement.dto.AnnouncementDto;
import com.cubas.studentmanagement.entity.User;
import com.cubas.studentmanagement.service.AnnouncementService;

@Controller
@RequestMapping("/announcements")
public class AnnouncementController {
	
	@Autowired
	AnnouncementService announcementService;

	@GetMapping("/list")
	public String getAnnouncements(Model model) {
		
		List<AnnouncementDto> announcements = null;
		
		try {
			announcements = announcementService.getAnnouncements();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("announcements", announcements);
		
		return "announcements/announcements-list";
	}
	
	@GetMapping("/details")
	public String showAnnouncement(@RequestParam("announcementId") Long id,
								   @AuthenticationPrincipal SchoolUserDetails schoolUserDetails,
								   Model model) {
		
		AnnouncementDto announcement = null;
		
		try {
			announcement = announcementService.findById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("announcement", announcement);
		if (schoolUserDetails != null) {
			model.addAttribute("curUser", schoolUserDetails);
		}
		
		return "announcements/announcements-post";
	}
	
	@GetMapping("/addAnnouncement")
	public String addAnnouncement(Model model,
								  @AuthenticationPrincipal SchoolUserDetails schoolUserDetails) {
		
		AnnouncementDto announcementDto = new AnnouncementDto();
		
		if (schoolUserDetails != null) {
			User curUser = schoolUserDetails.getUser();
			announcementDto.setUser(curUser);
			
			System.out.println("curUser = " + curUser);
			
			String curUserName = curUser.getFirstName() + ' ' + curUser.getLastName();
//			System.out.println("curUserName = " + curUserName);
			model.addAttribute("announcement", announcementDto);
			model.addAttribute("curUserName", curUserName);
		}
		
		return "announcements/announcements-form";
	}
	
	@GetMapping("/updateAnnouncement") 
	public String updateAnnouncement(@RequestParam("announcementId") Long id,
								     @AuthenticationPrincipal SchoolUserDetails schoolUserDetails,
									 Model model) {
		
		AnnouncementDto announcementDto = announcementService.findById(id);
		
//		Check to see if someone is logged in
//		Extra step, since this is already taken care of in our WebSecurityConfig file
		if (schoolUserDetails != null) {
			
//			Check to see if current user id matches the id of the author of post
			if (schoolUserDetails.getUser().getId() != announcementDto.getUser().getId()) {
				return "error/403";
			}
			else {
				model.addAttribute("announcement", announcementDto);
				
				return "announcements/announcements-form";				
			}
		}
		else {
			return "error/401";
		}
	}
	
	@PostMapping("/save")
	public String saveAnnouncement(@ModelAttribute("announcement") AnnouncementDto announcementDTO,
								   @AuthenticationPrincipal SchoolUserDetails schoolUserDetails) {

//		System.out.println(">>>>In saveAnnouncement()");
//		System.out.println("announceemntDTO = " + announcementDTO);

		announcementDTO.setUser(schoolUserDetails.getUser());
		
		if (announcementDTO.getTimestamp() == null) {
			announcementDTO.setTimestamp(new Timestamp(System.currentTimeMillis()));
		}
		
		announcementService.save(announcementDTO);
		
		return "redirect:/announcements/list";
	}
}
