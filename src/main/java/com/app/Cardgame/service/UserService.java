package com.app.Cardgame.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.Cardgame.model.Card;
import com.app.Cardgame.model.CardNotFoundException;
import com.app.Cardgame.model.Picture;
import com.app.Cardgame.model.Stack;
import com.app.Cardgame.model.User;
import com.app.Cardgame.model.UserNotFoundException;
import com.app.Cardgame.repo.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository repo;

	@Override
	public List<User> findAllUsers() {
		return repo.findAll();
	}

	@Override
	public User findUserById(String id) throws UserNotFoundException {
		return repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@Override
	public User findUserByUsername(String username) throws UserNotFoundException {
		List<User> uList = repo.findAll();
		Optional<User> found = uList.stream().filter(u -> u.getUsername().equals(username)).findFirst();
		return found.orElseThrow(() -> new UserNotFoundException(username));
	}

	@Override
	public User saveOrUpdateUser(String username, String email, String password) {
		return repo.save(new User(username, email, password));
	}

	@Override
	public Card getCardFromUser(String userid, String english) throws UserNotFoundException, CardNotFoundException {
		List<User> uList = repo.findAll();
		User user = uList.stream().filter(u -> u.getId().equals(userid)).findFirst()
				.orElseThrow(() -> new UserNotFoundException(userid));
		return user.getStack().getCardByEnglish(english);
	}

	@Override
	public Stack getStackFromUser(String userid) throws UserNotFoundException {
		List<User> uList = repo.findAll();
		User user = uList.stream().filter(u -> u.getId().equals(userid)).findFirst()
				.orElseThrow(() -> new UserNotFoundException(userid));
		return user.getStack();
	}

	@Override
	public Card addCard(String userid, String english, String spanish, String title, MultipartFile file)
			throws UserNotFoundException, IOException {
		User user = repo.findById(userid).orElseThrow(() -> new UserNotFoundException(userid));
		String fileName = file.getOriginalFilename();
		Binary image = new Binary(file.getBytes());
		Picture picture = new Picture(fileName, image);
		Card card = new Card(english, spanish, picture);
		user.getStack().addCard(card);
		repo.save(user);
		return card;
	}

	@Override
	public void removeCard(String userid, Card card) throws UserNotFoundException {
		User user = repo.findById(userid).orElseThrow(() -> new UserNotFoundException(userid));
		Stack stack = user.getStack();
		stack.removeCard(card);
	}

	@Override
	public void addPictureToCard(String userid, Card card, MultipartFile file)
			throws IOException, UserNotFoundException, CardNotFoundException {
		User user = repo.findById(userid).orElseThrow(() -> new UserNotFoundException(userid));
		Card findCard = user.getStack().getCardByEnglish(card.getEnglish());
		String fileName = file.getOriginalFilename();
		Binary image = new Binary(file.getBytes());
		Picture picture = new Picture(fileName, image);
		findCard.setPicture(picture);
	}

	@Override
	public void deleteById(String id) {
		repo.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();

	}

	@Override
	public Card makeCard(String english, String spanish, MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		Binary image = new Binary(file.getBytes());
		Picture picture = new Picture(fileName, image);
		return new Card(english, spanish, picture);
	}

	boolean isEmpty(Object o) {
		if (o == null) {
			System.out.println(o.toString() + " is null!");
			return true;
		}
		System.out.println(o.toString() + " is not null");
		return false;
	}
}
