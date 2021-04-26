package com.app.Cardgame.model;

public class Turn {
	private Pick pick;
	private Guess guess;
	private String result;

	public Turn() {
	}

	public Turn(Pick pick, Guess guess) {
		this.pick = pick;
		this.guess = guess;
	}

	public Pick getPick() {
		return pick;
	}

	public void setPick(Pick pick) {
		this.pick = pick;
	}

	public Guess getGuess() {
		return guess;
	}

	public void setGuess(Guess guess) {
		this.guess = guess;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
