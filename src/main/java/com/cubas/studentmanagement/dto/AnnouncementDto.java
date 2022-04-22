package com.cubas.studentmanagement.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cubas.studentmanagement.entity.Announcement;
import com.cubas.studentmanagement.entity.User;

public class AnnouncementDto {
	
	private Long id;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 100, message = "is required")
	private String title;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 1024, message = "is required")
	private String post;
	
	@NotNull(message = "is required")
	private Timestamp timestamp;
	
	@NotNull(message="is required")
	private User user;
	
//	Used to display formatted time
	private String formattedTimestamp;
	
//	Used to display times in local timezone of user
	private String timezone;

	private String authorName;

	public AnnouncementDto() {

	}

	public AnnouncementDto(
			@NotNull(message = "is required") @Size(min = 1, max = 100, message = "is required") String title,
			@NotNull(message = "is required") @Size(min = 1, max = 1024, message = "is required") String post,
			@NotNull(message = "is required") Timestamp timestamp,
			@NotNull(message = "is required") User user) {
		this.title = title;
		this.post = post;
		this.setTimestamp(timestamp);
		this.user = user;
		
		this.setAuthorName(user.getFirstName() + " " + user.getLastName());
	}

	public AnnouncementDto(Long id,
			@NotNull(message = "is required") @Size(min = 1, max = 100, message = "is required") String title,
			@NotNull(message = "is required") @Size(min = 1, max = 256, message = "is required") String post,
			@NotNull(message = "is required") Timestamp timestamp,
			@NotNull(message = "is required") User user) {
		this.id = id;
		this.title = title;
		this.post = post;
		this.setTimestamp(timestamp);
		this.user = user;
		
		this.setAuthorName(user.getFirstName() + " " + user.getLastName());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
//		Format the timestamp and save to formattedTimestamp
		String formattedTimestamp = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(timestamp);
		this.setFormattedTimestamp(formattedTimestamp);
//		System.out.println(formattedTimestamp);
		
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.setAuthorName(user.getFirstName() + " " + user.getLastName());
	}
	
	public String getFormattedTimestamp() {
		return formattedTimestamp;
	}
	
	public void setFormattedTimestamp(String formattedTimestamp) {
		this.formattedTimestamp = formattedTimestamp;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	public String getAuthorName() {
		
		return this.authorName;
	}
	
	public void setAuthorName(String authorName) {
		
		this.authorName = authorName;
	}
	
	public Announcement convertToAnnouncement() {
		
		Announcement announcement = new Announcement(
											this.id,
											this.title,
											this.post,
											this.timestamp,
											this.user);

		return announcement;
	}
	
	@Override
	public String toString() {
		return "AnnouncementDTO [id=" + id + ", title=" + title + ", post=" + post + ", timestamp=" + timestamp + ", schoolMember="
				+ user + "]";
	}
}
