package com.app.Cardgame.model;

public class Guess {

	private User user;
	private String guess;

	public Guess() {
	};

	public Guess(User user, String guess) {
		this.user = user;
		this.guess = guess;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGuess() {
		return guess;
	}

	public void setGuess(String guess) {
		this.guess = guess;
	}

	@Override
	public String toString() {
		return String.format("Guess[User=%s, guess=+s]", user, guess);
	}

}
