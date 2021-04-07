package com.app.Cardgame;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "card")
public class Card {

	@Id
	private String id;
	private String english;
	private String spanish;

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

	@Override
	public String toString() {
		return String.format("Card[id=%s, english=%s, spanish=%s]", id, english, spanish);
	}
}
