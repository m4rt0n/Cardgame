package com.app.Cardgame.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Cardgame.model.Card;
import com.app.Cardgame.model.Picture;
import com.app.Cardgame.model.User;
import com.app.Cardgame.repo.UserRepository;

@Service
public class GameService implements IGameService {
	@Autowired
	UserRepository repo;

	static String[] paths = { "D:\\Prog\\Cardgame\\images\\dog.jpg", "D:\\Prog\\Cardgame\\images\\cat.jpg",
			"D:\\Prog\\Cardgame\\images\\sun.jpg", "D:\\Prog\\Cardgame\\images\\moon.jpg" };

	public List<Picture> createPictures() throws IOException {
		List<Picture> pictures = new ArrayList<Picture>();
		for (String path : paths) {
			Path p = Paths.get(path);
			String title = p.getFileName().toString();
			byte[] bytes = Files.readAllBytes(p);
			pictures.add(new Picture(title, bytes));
		}
		return pictures;
	}

	public List<Card> createCards(List<Picture> pictures) {
		Card dogCard = new Card("dog", "el perro", pictures.get(0));
		Card catCard = new Card("cat", "la gata", pictures.get(1));
		Card sunCard = new Card("sun", "el sol", pictures.get(3));
		Card moonCard = new Card("moon", "la luna", pictures.get(4));
		return Stream.of(dogCard, catCard, moonCard, sunCard).collect(Collectors.toList());
		// return new ArrayList<>(Arrays.asList(dogCard, catCard, moonCard, sunCard));
	}

	public void createUsers() throws IOException {
		List<Card> cards = createCards(createPictures());
		User user1 = new User("user1", "u1pw", "u1@u.com");
		User user2 = new User("user2", "u2pw", "u2@u.com");
		user1.getStack().addCard(cards.get(0));
		user1.getStack().addCard(cards.get(1));
		user2.getStack().addCard(cards.get(2));
		user2.getStack().addCard(cards.get(3));
		repo.save(user1);
		repo.save(user2);
		System.out.println("created: " + user1.toString());
		System.out.println("created: " + user2.toString());
	}

}
