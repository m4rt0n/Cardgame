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
	public void loadPlayers(@RequestParam(value = "player1id") String player1id,
			@RequestParam(value = "player2id") String player2id) throws UserNotFoundException, CardNotFoundException {
		gService.loadPlayers(player1id, player2id);
	}

	@PostMapping("/startgame")
	public void startGame() throws CardNotFoundException, UserNotFoundException {
		gService.startGame();
	}

}
