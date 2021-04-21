package com.app.Cardgame.service;

import java.io.IOException;
import java.util.List;

import com.app.Cardgame.model.CardNotFoundException;
import com.app.Cardgame.model.Guess;
import com.app.Cardgame.model.Pick;
import com.app.Cardgame.model.User;
import com.app.Cardgame.model.UserNotFoundException;

public interface IGameService {

	void createPlayers() throws IOException;

	List<User> loadPlayers(String p1id, String p2id) throws UserNotFoundException, CardNotFoundException;

	void startGame() throws CardNotFoundException, UserNotFoundException;

	Pick createPick(String uid, String english, String ask) throws UserNotFoundException, CardNotFoundException;

	Guess createGuess(String uid, String suess) throws UserNotFoundException;

	void check();

}
