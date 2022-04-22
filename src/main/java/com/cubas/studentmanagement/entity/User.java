package com.cubas.studentmanagement.entity;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="first_name")
	@NotEmpty(message="First name cannot be empty")
	private String firstName;

	@Column(name="last_name")
	@NotEmpty(message="Last name cannot be empty")
	private String lastName;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="birthday")
	private LocalDate birthday;

	@Email
	@Column(name="personal_email")
	@NotEmpty(message="Email cannot be empty")
	private String personalEmail;
	
	@Email
	@Column(name="school_email")
	private String schoolEmail;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "belongs",
			   joinColumns = @JoinColumn(name = "instructor_id"),
			   inverseJoinColumns = @JoinColumn(name = "department_id"))
	private Department instructorDepartment;

	@ManyToMany(fetch = FetchType.EAGER,
				cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles",
			   joinColumns = @JoinColumn(name = "user_id"),
			   inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	@Column(name="enabled")
	private boolean enabled;

	// Needed for Hibernate
	public User() {
		
	}

	public User(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public User(String firstName, String lastName, String username, String password,
			@Email String schoolEmail, Collection<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.schoolEmail = schoolEmail;
		this.roles = roles;
	}

	public User(Long id, @NotEmpty String firstName, @NotEmpty String lastName, String username, String password,
			@NotNull LocalDate birthday, @Email @NotEmpty String personalEmail, @Email String schoolEmail,
			Department instructorDepartment, Collection<Role> roles, boolean enabled) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.personalEmail = personalEmail;
		this.schoolEmail = schoolEmail;
		this.instructorDepartment = instructorDepartment;
		this.roles = roles;
		this.enabled = enabled;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getSchoolEmail() {
		return schoolEmail;
	}

	public void setSchoolEmail(String schoolEmail) {
		this.schoolEmail = schoolEmail;
	}

	public Department getInstructorDepartment() {
		return instructorDepartment;
	}

	public void setInstructorDepartment(Department instructorDepartment) {
		this.instructorDepartment = instructorDepartment;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", birthday=" + birthday + ", personalEmail=" + personalEmail
				+ ", schoolEmail=" + schoolEmail + ", roles=" + roles + ", enabled=" + enabled + "]";
	}
}
