package com.app.Cardgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stack")
public class Stack {

	private List<Card> cards;

	public Stack() {
		cards = new ArrayList<Card>();
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void addCards(List<Card> cardList) {
		cardList.stream().forEach(c -> cards.add(c));
	}

	public void removeCard(Card card) {
		cards.remove(card);
	}

	public Card getCardByEnglish(String english) throws CardNotFoundException {
		Optional<Card> card = cards.stream().filter(c -> c.getEnglish().equals(english)).findFirst();
		return card.orElseThrow(() -> new CardNotFoundException(english));
	}

	public Card getCardBySpanish(String spanish) throws CardNotFoundException {
		Optional<Card> card = cards.stream().filter(c -> c.getSpanish().equals(spanish)).findFirst();
		return card.orElseThrow(() -> new CardNotFoundException(spanish));
	}

	@Override
	public String toString() {
		return String.format("Stack[cards=%s]", cards);
	}

}
