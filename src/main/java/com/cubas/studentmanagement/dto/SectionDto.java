package com.cubas.studentmanagement.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.cubas.studentmanagement.entity.Course;
import com.cubas.studentmanagement.entity.Section;
import com.cubas.studentmanagement.entity.User;

public class SectionDto {
	
	private Long id;
	
//	@NotNull(message = "is required")
	private Course course;
	
	@Min(1)
	@NotNull(message = "is required")
	private Long sectionId;
	
	private User instructor;
	
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "is required")
	private LocalDate startDate;
	
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "is required")
	private LocalDate endDate;

	@DateTimeFormat(pattern = "HH:mm")
	@NotNull(message = "is required")
	private LocalTime startTime;
	
	@DateTimeFormat(pattern = "HH:mm")
	@NotNull(message = "is required")
	private LocalTime endTime;
	
	@NotNull(message = "is required")
	private Map<String, Boolean> meetingDays;
	
	@NotNull(message = "is required")
	private Long size;
	
//	Used for display
	private Boolean sunday;
	private Boolean monday;
	private Boolean tuesday;
	private Boolean wednesday;
	private Boolean thursday;
	private Boolean friday;
	private Boolean saturday;
	private String instructorName;
	private String formattedStartDate;
	private String formattedEndDate;
	private String formattedStartTime;
	private String formattedEndTime;
	private String meetingDaysString;
	
	public SectionDto() {
		
	}

	public SectionDto(Long id, 
			 		  Course course,
			 		  @Min(1) @NotNull(message = "is required") Long sectionId, 
			 		  User instructor,
			 		  @Future @NotNull(message = "is required") LocalDate startDate,
			 		  @Future @NotNull(message = "is required") LocalDate endDate,
			 		  @NotNull(message = "is required") LocalTime startTime, 
			 		  @NotNull(message = "is required") LocalTime endTime,
			 		  Boolean sunday, 
			 		  Boolean monday, 
			 		  Boolean tuesday, 
			 		  Boolean wednesday, 
			 		  Boolean thursday, 
			 		  Boolean friday, 
			 		  Boolean saturday, 
			 		  @NotNull(message = "is required") Long size) {
		this.id = id;
		this.course = course;
		this.sectionId = sectionId;
		this.instructor = instructor;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.sunday = sunday;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
		this.setMeetingDays();
		this.size = size;
		
		this.setMeetingDaysString(this.meetingDays);
		this.formatDateAndTime();
	}
	
	public SectionDto(Section section) {
		
		this(section.getId(),
			 section.getCourse(),
			 section.getSectionId(),
			 section.getInstructor(),
			 section.getStartDate(),
			 section.getEndDate(),
			 section.getStartTime(),
			 section.getEndTime(),
			 section.isSunday(),
			 section.isMonday(),
			 section.isTuesday(),
			 section.isWednesday(),
			 section.isThursday(),
			 section.isFriday(),
			 section.isSaturday(),
			 section.getSize());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public User getInstructor() {
		return instructor;
	}

	public void setInstructor(User instructor) {
		this.instructor = instructor;
		this.instructorName = instructor.getFirstName() + ' ' + instructor.getLastName();
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
		this.formattedStartDate = startDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
		this.formattedEndDate = endDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	}
	
	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
		this.formattedStartTime = startTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
		this.formattedEndTime = endTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
	}

	public Map<String, Boolean> getMeetingDays() {
		return meetingDays;
	}

	public void setMeetingDays(Map<String, Boolean> meetingDays) {
		this.meetingDays = meetingDays;
	}
	
	public String getMeetingDaysString() {
		return this.meetingDaysString;
	}
	
	public void setMeetingDaysString(String meetingDaysString) {
		this.meetingDaysString = meetingDaysString;
	}

	public void setMeetingDaysString(Map<String, Boolean> meetingDays) {
		
		StringBuilder daysOfWeek = new StringBuilder();
		String[] days = {"M", "Tu", "W", "Th", "F", "S", "Su"};
		
//		System.out.println(">>>>In setMeetingDaysString()");
		
		for (String day : days) {
//			System.out.println("day = " + day);
			if (this.meetingDays.get(day) == true) {
				daysOfWeek.append(day);
			}
		}
		
		this.meetingDaysString = daysOfWeek.toString();
	}

	public void setMeetingDays() {
		
		this.meetingDays = new HashMap<>();

		meetingDays.put("Su", this.sunday);
		meetingDays.put("M", this.monday);
		meetingDays.put("Tu", this.tuesday);
		meetingDays.put("W", this.wednesday);
		meetingDays.put("Th", this.thursday);
		meetingDays.put("F", this.friday);
		meetingDays.put("S", this.saturday);
	}
	
	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
	
	public String getFormattedStartDate() {
		return formattedStartDate;
	}

	public void setFormattedStartDate(String formattedStartDate) {
		this.formattedStartDate = formattedStartDate;
	}

	public String getFormattedEndDate() {
		return formattedEndDate;
	}

	public void setFormattedEndDate(String formattedEndDate) {
		this.formattedEndDate = formattedEndDate;
	}

	public String getFormattedStartTime() {
		return formattedStartTime;
	}

	public void setFormattedStartTime(String formattedStartTime) {
		this.formattedStartTime = formattedStartTime;
	}

	public String getFormattedEndTime() {
		return formattedEndTime;
	}

	public void setFormattedEndTime(String formattedEndTime) {
		this.formattedEndTime = formattedEndTime;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	
	public Boolean getSunday() {
		return sunday;
	}

	public void setSunday(Boolean sunday) {
		this.sunday = sunday;
	}

	public Boolean getMonday() {
		return monday;
	}

	public void setMonday(Boolean monday) {
		this.monday = monday;
	}

	public Boolean getTuesday() {
		return tuesday;
	}

	public void setTuesday(Boolean tuesday) {
		this.tuesday = tuesday;
	}

	public Boolean getWednesday() {
		return wednesday;
	}

	public void setWednesday(Boolean wednesday) {
		this.wednesday = wednesday;
	}

	public Boolean getThursday() {
		return thursday;
	}

	public void setThursday(Boolean thursday) {
		this.thursday = thursday;
	}

	public Boolean getFriday() {
		return friday;
	}

	public void setFriday(Boolean friday) {
		this.friday = friday;
	}

	public Boolean getSaturday() {
		return saturday;
	}

	public void setSaturday(Boolean saturday) {
		this.saturday = saturday;
	}

	private void formatDateAndTime() {
		
		this.formattedStartDate = this.startDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.formattedEndDate = this.endDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		
		this.formattedStartTime = this.startTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
		this.formattedEndTime = this.endTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
	}
	
	public Section convertToSection() {
		
		Section section = new Section(this.id,
									  this.course,
									  this.sectionId,
									  this.instructor,
									  this.startDate,
									  this.endDate,
									  this.startTime,
									  this.endTime,
									  this.meetingDays.get("Su"),
									  this.meetingDays.get("M"),
									  this.meetingDays.get("Tu"),
									  this.meetingDays.get("W"),
									  this.meetingDays.get("Th"),
									  this.meetingDays.get("F"),
									  this.meetingDays.get("S"),
									  this.size);
		
		return section;
	}

//	@Override
//	public String toString() {
//		return "SectionDto [sunday=" + sunday + ", monday=" + monday + ", tuesday=" + tuesday + ", wednesday="
//				+ wednesday + ", thursday=" + thursday + ", friday=" + friday + ", saturday=" + saturday + "]";
//	}

	@Override
	public String toString() {
		return "SectionDto [id=" + id + ", sectionId=" + sectionId + ", instructor=" + instructor + ", startDate="
				+ startDate + ", endDate=" + endDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", meetingDays=" + meetingDays + ", size=" + size + ", instructorName=" + instructorName
				+ ", formattedStartDate=" + formattedStartDate + ", formattedEndDate=" + formattedEndDate
				+ ", formattedStartTime=" + formattedStartTime + ", formattedEndTime=" + formattedEndTime
				+ ", meetingDaysString=" + meetingDaysString + "]";
	}

	
	
}
