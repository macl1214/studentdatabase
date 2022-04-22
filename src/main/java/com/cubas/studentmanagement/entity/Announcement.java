package com.cubas.studentmanagement.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "announcement")
public class Announcement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="title")
	private String title;

	@Column(name="post")
	private String post;
	
	@Column(name="timestamp")
	private Timestamp timestamp;
	
//	Name passed to @JoinColumn is the name of a column in the announcements table 
	@ManyToOne(fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						  CascadeType.DETACH, CascadeType.MERGE})
	@JoinColumn(name="admin_id")
	private User user;
	
	public Announcement() {
		
	}

	public Announcement(Long id, String title, String post, Timestamp timestamp, User user) {
		this.id = id;
		this.title = title;
		this.post = post;
		this.timestamp = timestamp;
		this.user = user;
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
	
	public void setTItle(String title) {
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
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void getUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Announcement [id=" + id + ", title=" + title + ", post=" + post + ", timestamp=" + timestamp + ", schoolMember=" + user
				+ "]";
	}
	
}
