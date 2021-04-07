package com.app.Cardgame;

public class CardNotFoundException extends Exception {
	private String id;

	public CardNotFoundException(String param) {
		super(String.format("card is not found: %s", param));
	}
}
