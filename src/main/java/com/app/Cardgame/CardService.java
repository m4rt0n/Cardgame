package com.app.Cardgame;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CardService implements ICardService {

	@Autowired
	CardRepository cRepo;

	@Override
	public List<Card> findAll() {
		return cRepo.findAll();
	}

	@Override
	public List<Picture> findAllPictures() {
		List<Card> cList = cRepo.findAll();
		return cList.stream().map(c -> c.getPicture()).collect(Collectors.toList());
	}

	@Override
	public Card findById(String id) throws CardNotFoundException {
		return cRepo.findById(id).orElseThrow(() -> new CardNotFoundException(id));
	}

	@Override
	public Card findByEnglish(String english) throws CardNotFoundException {
		List<Card> cList = cRepo.findAll();
		Optional<Card> found = cList.stream().filter(c -> c.getEnglish().equals(english)).findFirst();
		return found.orElseThrow(() -> new CardNotFoundException(english));
	}

	@Override
	public Card findBySpanish(String spanish) throws CardNotFoundException {
		List<Card> list = cRepo.findAll();
		Optional<Card> foundCard = list.stream().filter(c -> c.getSpanish().equals(spanish)).findFirst();
		return foundCard.orElseThrow(() -> new CardNotFoundException(spanish));
	}

	@Override
	public Card saveOrUpdate(String english, String spanish, MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		Binary image = new Binary(file.getBytes());
		Picture picture = new Picture(fileName, image);
		Card card = new Card(english, spanish, picture);
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
