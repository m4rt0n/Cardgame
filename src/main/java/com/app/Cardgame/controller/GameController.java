package com.app.Cardgame.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Cardgame.model.CardNotFoundException;
import com.app.Cardgame.model.UserNotFoundException;
import com.app.Cardgame.service.IGameService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping(path = "/game")
public class GameController {
	@Autowired
	IGameService gService;

	@GetMapping("/hello")
	public @ResponseBody String hello() {
		return "hello from game controller";
	}

	@PostMapping("/createplayers")
	public void createUsers() throws IOException {
		gService.createPlayers();
	}

	@PostMapping("/loadplayers")
	public void loadPlayers(@RequestParam(value = "user1id") String user1id,
			@RequestParam(value = "user2id") String user2id) throws UserNotFoundException, CardNotFoundException {
		gService.loadPlayers(user1id, user2id);
	}

	@PostMapping("/startgame")
	public void startGame() throws CardNotFoundException, UserNotFoundException {
		gService.startGame();
	}

	@PostMapping("/pick")
	public void pick(@RequestParam(value = "userid") String userid, @RequestParam(value = "english") String english,
			@RequestParam(value = "ask") String ask) throws UserNotFoundException, CardNotFoundException {
		gService.createPick(userid, english, ask);
	}

	@PostMapping("/guess")
	public void guess(@RequestParam(value = "userid") String userid, @RequestParam(value = "guess") String guess)
			throws UserNotFoundException {
		gService.createGuess(userid, guess);
	}

	@PostMapping("/checkturn")
	public void check() {
		gService.checkTurn();
	}

	@PostMapping("/finishgame")
	public void finishGame() {
		gService.finishgame();
	}
}
