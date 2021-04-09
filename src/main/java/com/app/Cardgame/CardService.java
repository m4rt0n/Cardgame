package com.app.Cardgame;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService implements ICardService {

	@Autowired
	CardRepository cRepo;

	@Override
	public List<Card> findAll() {
		return cRepo.findAll();
	}

	@Override
	public Card findById(String id) throws CardNotFoundException {
		return cRepo.findById(id).orElseThrow(() -> new CardNotFoundException(id));
	}

	@Override
	public Card findByEnglish(String english) throws CardNotFoundException {
		List<Card> list = cRepo.findAll();
		Optional<Card> found = list.stream().filter(c -> c.getEnglish().equals(english)).findFirst();
		return found.orElseThrow(() -> new CardNotFoundException(english));
	}

	@Override
	public Card findBySpanish(String spanish) throws CardNotFoundException {
		List<Card> list = cRepo.findAll();
		Optional<Card> found = list.stream().filter(c -> c.getSpanish().equals(spanish)).findFirst();
		return found.orElseThrow(() -> new CardNotFoundException(spanish));
	}

	@Override
	public Card saveOrUpdate(Card card) {
		return cRepo.save(card);
	}

	@Override
	public void deleteById(String id) {
		cRepo.deleteById(id);
	}

	@Override
	public void deleteAll() {
		cRepo.deleteAll();

	}

}
