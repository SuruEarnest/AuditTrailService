package com.s3.systems.crm.auditlog.app.models;

import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
	@Id
	private long userId;
	private String userRole;
	@NotBlank(message = "firstname is mandatory")
	private String firstname;
	private String lastname;
	@NotBlank(message = "username is mandatory")
	private String username;
	private transient String password;

	public User() {
	}

	public User(String username, String password, String userRole) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
