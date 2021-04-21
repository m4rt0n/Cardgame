package com.app.Cardgame.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Cardgame.model.Card;
import com.app.Cardgame.model.CardNotFoundException;
import com.app.Cardgame.model.Guess;
import com.app.Cardgame.model.Pick;
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
	}

	Pick turnPick;
	Guess turnGuess;

	@Override
	public void check() {
		String answer = turnGuess.getGuessWord();
		String engQuestion = turnPick.getCard().getEnglish();
		String espQuestion = turnPick.getCard().getSpanish();
		Card pickedCard = turnPick.getCard();
		User guesser = turnGuess.getUser();
		String question;
		System.out.println(String.format("card: %s", pickedCard.toString()));

		if (turnPick.isAskedEnglish()) {
			question = new String(engQuestion);
		} else if (turnPick.isAskedSpanish()) {
			question = new String(espQuestion);
		} else {
			question = new String("problem with question");
		}

		System.out.println(String.format("question: %s : answer: %s", question, answer));
		if (answer.equals(question)) {
			System.out.println("card guessed, picker's card added to guesser's stack");
			guesser.getStack().addCard(pickedCard);
			repo.save(guesser);
		} else {
			System.out.println("not guessed");
		}
		turnPick = null;
		turnGuess = null;
	}

	@Override
	public Pick createPick(String uid, String english, String ask) throws UserNotFoundException, CardNotFoundException {
		User user = uService.findUserById(uid);
		Card card = user.getStack().getCardByEnglish(english);
		Pick pick = new Pick(user, card);
		setPickAskState(pick, ask);
		System.out.println(pick.toString());
		turnPick = pick;
		return pick;
	}

	@Override
	public Guess createGuess(String uid, String guessWord) throws UserNotFoundException {
		User user = uService.findUserById(uid);
		Guess guess = new Guess(user, guessWord);
		System.out.println(guess.toString());
		turnGuess = guess;
		return guess;
	}

	public void setPickAskState(Pick pick, String ask) {
		if (ask.equals("english")) {
			pick.setAskedEnglish(true);
		} else if (ask.equals("spanish")) {
			pick.setAskedSpanish(true);
		} else {
			System.out.println("'ask' field should be either 'english' or 'spanish'");
		}
	}

}
