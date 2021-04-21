package com.app.Cardgame.model;

public class Pick {

	private User user;
	private Card card;
	boolean askedEnglish;
	boolean askedSpanish;

	public Pick() {
	};

	public Pick(User user, Card card) {
		this.user = user;
		this.card = card;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public boolean isAskedEnglish() {
		return askedEnglish;
	}

	public void setAskedEnglish(boolean askedEnglish) {
		this.askedEnglish = askedEnglish;
	}

	public boolean isAskedSpanish() {
		return askedSpanish;
	}

	public void setAskedSpanish(boolean askedSpanish) {
		this.askedSpanish = askedSpanish;
	}

	@Override
	public String toString() {
		return String.format("Pick[User=%s, Card=%s, askedEnglish=%s, askedSpanish=%s]", user, card, askedEnglish,
				askedSpanish);
	}
}
