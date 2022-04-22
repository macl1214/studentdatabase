package com.cubas.studentmanagement.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SchoolMemberDto {
	
	private Long id;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String lastName;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String username;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@Email
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;
	
	private String formRole;
	
	private String fullName;
	
	public SchoolMemberDto() {
		
	}
	
	
}