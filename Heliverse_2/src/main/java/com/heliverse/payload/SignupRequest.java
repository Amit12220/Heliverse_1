package com.heliverse.payload;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.*;


public class SignupRequest {	
	
	@Size(min=4, message ="Name must have a minimum of 4 characters" )
	@NotEmpty(message = "Name must not be empty")
	private String userName;
	
	@NotEmpty(message = "Email must not be empty")
	@Email(message = "Email is not valid")
	private String email;	
	
	@Past(message = "Date of birth must be in the past")
	@NotNull(message = "DOB must not be empty")
	private LocalDate dob;
	
	@Pattern(regexp = "\\d{10}", message="Phone number must be 10 digits")
	@NotEmpty(message = "Phone must not be empty")
	private String phone;
	
	@Size(min=8,message = "Password size must be at least 8 characters")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$",
    message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit")
	@NotEmpty(message = "Password must not be empty")
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	private Set<String> roles;

	public SignupRequest(
			@Size(min = 4, message = "Name must have a minimum of 4 characters") @NotEmpty(message = "Name must not be empty") String name,
			@NotEmpty(message = "Email must not be empty") @Email(message = "Email is not valid") String email,
			@Past(message = "Date of birth must be in the past") @NotNull(message = "DOB must not be empty") LocalDate dob,
			@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits") @NotEmpty(message = "Phone must not be empty") String phone,
			@Size(min = 8, message = "Password size must be at least 8 characters") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit") @NotEmpty(message = "Password must not be empty") String password,
			Set<String> roles) {
		super();
		this.userName = name;
		this.email = email;
		this.dob = dob;
		this.phone = phone;
		this.password = password;
		this.roles = roles;
	}

	public SignupRequest() {
		super();
	}

	public String getName() {
		return userName;
	}

	public void setName(String name) {
		this.userName = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRole(Set<String> roles) {
		this.roles = roles;
	}
	
	
}
