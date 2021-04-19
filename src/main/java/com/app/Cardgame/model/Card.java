package com.app.Cardgame.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cards")
public class Card {

	private String english;
	private String spanish;
	private Picture picture;

	public Card() {
	}

	public Card(String english, String spanish, Picture picture) {
		this.english = english;
		this.spanish = spanish;
		this.picture = picture;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getSpanish() {
		return spanish;
	}

	public void setSpanish(String spanish) {
		this.spanish = spanish;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return String.format("Card[english=%s, spanish=%s, picture=%s]", english, spanish, picture);
	}
}
