package com.app.Cardgame.service;

import java.io.IOException;
import java.util.List;

import com.app.Cardgame.model.CardNotFoundException;
import com.app.Cardgame.model.User;
import com.app.Cardgame.model.UserNotFoundException;

public interface IGameService {

	void createPlayers() throws IOException;

	List<User> loadPlayers(String u1id, String u2id) throws UserNotFoundException, CardNotFoundException;

	void startGame() throws CardNotFoundException, UserNotFoundException;

}
