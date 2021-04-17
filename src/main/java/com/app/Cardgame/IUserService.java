package com.app.Cardgame;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IUserService {

	List<User> findAllUsers();

	User findUserById(String id) throws UserNotFoundException;

	User findUserByUsername(String username) throws UserNotFoundException;

	User saveOrUpdateUser(String username, String password, String email);

	Card getCardFromUser(String userid, String cardid) throws UserNotFoundException, CardNotFoundException;

	Card makeCard(String english, String spanish, MultipartFile file) throws IOException;

	Card addCard(String userid, String english, String spanish, String title, MultipartFile file)
			throws UserNotFoundException, IOException;

	Stack getStackFromUser(String userid) throws UserNotFoundException;

	void removeCard(String userid, Card card) throws UserNotFoundException;

	void addPictureToCard(String userid, Card card, MultipartFile file)
			throws IOException, UserNotFoundException, CardNotFoundException;

	void deleteById(String id);

	void deleteAll();

}
