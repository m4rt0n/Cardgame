package com.app.Cardgame.service;

import java.io.IOException;
import java.util.List;

import com.app.Cardgame.model.CardNotFoundException;
import com.app.Cardgame.model.User;
import com.app.Cardgame.model.UserNotFoundException;

public interface IGameService {
	public void createPlayers() throws IOException;

	public List<User> loadPlayersForGame(String u1id, String u2id) throws UserNotFoundException, CardNotFoundException;
}
