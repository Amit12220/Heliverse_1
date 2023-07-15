package com.heliverse.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name="USERS",uniqueConstraints = { 
	      @UniqueConstraint(columnNames = "email") 
	    })

public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	
	
	private String customerName;

	private String email;	
	
	private LocalDate dob;
	
	private String phone;
	
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles=new HashSet<>();

	

	public Customer() {
		super();
	}

	

	public Customer(String name, String email, LocalDate dob, String phone, String password) {
		super();
		this.customerName = name;
		this.email = email;
		this.dob = dob;
		this.phone = phone;
		this.password = password;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return customerName;
	}



	public void setName(String name) {
		this.customerName = name;
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



	public Set<Role> getRoles() {
		return roles;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



	
	
	
}
