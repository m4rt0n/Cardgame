package com.app.Cardgame.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Cardgame.model.Card;
import com.app.Cardgame.model.CardNotFoundException;
import com.app.Cardgame.model.Picture;
import com.app.Cardgame.model.User;
import com.app.Cardgame.model.UserNotFoundException;
import com.app.Cardgame.repo.UserRepository;

@Service
public class GameService implements IGameService {
	@Autowired
	UserRepository repo;

	@Autowired
	IUserService uService;

	static String[] paths = { "D:\\Prog\\Cardgame\\images\\dog.jpg", "D:\\Prog\\Cardgame\\images\\cat.jpg",
			"D:\\Prog\\Cardgame\\images\\sun.jpg", "D:\\Prog\\Cardgame\\images\\moon.jpg" };

	private List<User> players = new ArrayList<User>();

	public List<Picture> createPictures() throws IOException {
		List<Picture> pictures = new ArrayList<Picture>();
		for (String path : paths) {
			Path p = Paths.get(path);
			String title = p.getFileName().toString();
			Binary bytes = new Binary(Files.readAllBytes(p));
			pictures.add(new Picture(title, bytes));
		}
		return pictures;
	}

	public List<Card> createCards(List<Picture> pictures) {
		Card dogCard = new Card("dog", "el perro", pictures.get(0));
		Card catCard = new Card("cat", "la gata", pictures.get(1));
		Card sunCard = new Card("sun", "el sol", pictures.get(2));
		Card moonCard = new Card("moon", "la luna", pictures.get(3));
		return Stream.of(dogCard, catCard, moonCard, sunCard).collect(Collectors.toList());
		// return new ArrayList<>(Arrays.asList(dogCard, catCard, moonCard, sunCard));
	}

	@Override
	public void createPlayers() throws IOException {
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

	public List<User> loadPlayers(String u1id, String u2id) throws UserNotFoundException, CardNotFoundException {
		User u1 = uService.findUserByUsername("user1");
		User u2 = uService.findUserByUsername("user2");
		System.out.println(String.format("loaded users: \n%s \n%s", u1.toString(), u2.toString()));
		players = Stream.of(u1, u2).collect(Collectors.toList());
		return players;
	}

	@Override
	public void startGame() throws CardNotFoundException, UserNotFoundException {

		User u1 = players.get(0);
		User u2 = players.get(1);
		System.out.println(String.format("game started with users: \n%s, \n%s", u1.toString(), u2.toString()));
		Scanner sc = new Scanner(System.in);
		System.out.println("turn 1");
		System.out.println("u1: pick a card (english)");
		String u1pick = sc.nextLine();
		Card u1Card = u1.getStack().getCardByEnglish(u1pick);
		System.out.println("u1 picked card: " + u1Card.getEnglish());
		System.out.println("u2 turn: guess u1 card (spanish)");
		String u2guess = sc.nextLine();
		if (u1Card.getSpanish().equals(u2guess)) {
			System.out.println("u2 guessed u1 card - card added to u2 stack");
			u2.getStack().addCard(u1Card);
			repo.save(u2);
		} else {
			System.out.println("u2 didn't guess u1 card");
		}

	}

}
