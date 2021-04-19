package com.app.Cardgame.model;

public class UserNotFoundException extends Exception {

	public UserNotFoundException(String param) {
		super(String.format("user is not found: %s", param));
	}
}
