package com.app.Cardgame;

import java.util.List;

public interface ICardService {

	List<Card> findAll();

	Card findById(String id) throws CardNotFoundException;

	Card findByEnglish(String english) throws CardNotFoundException;

	Card findBySpanish(String spanish) throws CardNotFoundException;

	Card saveOrUpdate(Card card);

	void deleteById(String id);

	void deleteAll();

}
