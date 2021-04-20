package com.app.Cardgame.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.Cardgame.model.Card;
import com.app.Cardgame.model.CardNotFoundException;
import com.app.Cardgame.model.Stack;
import com.app.Cardgame.model.User;
import com.app.Cardgame.model.UserNotFoundException;
import com.app.Cardgame.service.IUserService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping(path = "/users")
public class UserController {
	@Autowired
	IUserService uService;

	@GetMapping("/hello")
	public @ResponseBody String hello() {
		return "hello from user controller";
	}

	@GetMapping("/getallusers")
	public Iterable<User> getAll() {
		return uService.findAllUsers();
	}

	@GetMapping("/getuserbyid")
	public User getById(@RequestParam(value = "id") String id) throws UserNotFoundException {
		return uService.findUserById(id);
	}

	@GetMapping("/getuserbyusername")
	public User getUserByUsername(@RequestParam(value = "username") String username) throws UserNotFoundException {
		return uService.findUserByUsername(username);
	}

	@PostMapping("/createuser")
	public User saveOrUpdateUser(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, @RequestParam(value = "email") String email) {
		return uService.saveOrUpdateUser(username, password, email);
	}

	@GetMapping("/getuserstack")
	public Stack getUserStack(@RequestParam(value = "userid") String userid) throws UserNotFoundException {
		return uService.getStackFromUser(userid);
	}

	@GetMapping("/getcardfromuser")
	public Card getCardFromUser(@RequestParam(value = "userid") String userid,
			@RequestParam(value = "english") String english) throws UserNotFoundException, CardNotFoundException {
		return uService.getCardFromUser(userid, english);
	}

	@PostMapping("/addcard")
	public void addCardToUser(@RequestParam(value = "userid") String userid,
			@RequestParam(value = "english") String english, @RequestParam(value = "spanish") String spanish,
			@RequestParam(value = "title") String title, @RequestParam(value = "image") MultipartFile file)
			throws UserNotFoundException, IOException {
		uService.addCard(userid, english, spanish, title, file);
	}

	@DeleteMapping("/deleteuserbyid/{id}")
	public void deleteById(@PathVariable("id") String id) {
		uService.deleteById(id);
	}

	@DeleteMapping("/deleteall")
	public void deleteAllPerson() {
		uService.deleteAll();
	}
}
