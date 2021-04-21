package com.app.Cardgame.model;

public class Guess {

	private User user;
	private String guessWord;

	public Guess() {
	};

	public Guess(User user, String guessWord) {
		this.user = user;
		this.guessWord = guessWord;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGuessWord() {
		return guessWord;
	}

	public void setGuessWord(String guess) {
		this.guessWord = guess;
	}

	@Override
	public String toString() {
		return String.format("Guess[User=%s, guess=%s]", user, guessWord);
	}

}
