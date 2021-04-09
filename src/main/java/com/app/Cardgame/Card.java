package com.app.Cardgame;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cards")
public class Card {

	@Id
	private String id;
	private String english;
	private String spanish;
	private Picture picture;

	public Card() {
	}

	public Card(String english, String spanish) {
		this.english = english;
		this.spanish = spanish;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return String.format("Card[id=%s, english=%s, spanish=%s, picture=%s]", id, english, spanish, picture);
	}
}
