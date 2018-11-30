package com.costsestimationbackend.costsestimationbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {

	public User() {
	}

	public User(User user) {
//		this.active = users.getActive();
//		this.email = users.getEmail();
		this.role = user.getRole();
//		this.name = users.getName();
//		this.lastName =users.getLastName();
		this.id = user.getId();
		this.password = user.getPassword();
		this.login = user.getLogin();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

	private String login;

	private String password;

	private String role;

}

