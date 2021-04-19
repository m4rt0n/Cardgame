package com.app.Cardgame.model;

import org.springframework.data.annotation.Id;

//@Document(collection = "users")
public class User {

	@Id
	private String id;
	private String username;
	private String password;
	private String email;
	private Stack stack;

	public User() {
	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		stack = new Stack();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Stack getStack() {
		return stack;
	}

	public void setStack(Stack stack) {
		this.stack = stack;
	}

	@Override
	public String toString() {
		return String.format("User[id=%s, username=%s, password=%s, email=%s, stack=%s]", id, username, password, email,
				stack);
	}
}
