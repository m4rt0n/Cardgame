package com.app.Cardgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/card")
public class CardController {
	@Autowired
	ICardService service;

	@GetMapping("/hello")
	public @ResponseBody String hello() {
		return "hello";
	}

	@PostMapping("/addcards")
	public void addCards() {
		service.saveOrUpdate(new Card("dog", "perro"));
		service.saveOrUpdate(new Card("cat", "gato"));
		service.saveOrUpdate(new Card("flower", "flor"));
	}

	@GetMapping("/getall")
	public Iterable<Card> getAll() {
		return service.findAll();
	}

	@GetMapping("/getbyid/{id}")
	public Card getById(@PathVariable("id") String id) throws CardNotFoundException {
		return service.findById(id);
	}

	@GetMapping("/getbyenglish/{english}")
	public Card getByEnglish(@PathVariable("english") String english) throws CardNotFoundException {
		return service.findByEnglish(english);
	}

	@GetMapping("/getbyspanish/{spanish}")
	public Card getBySpanish(@PathVariable("spanish") String spanish) throws CardNotFoundException {
		return service.findByEnglish(spanish);
	}

	@PostMapping("/save")
	public void saveOrUpdate(@RequestBody Card card) {
		service.saveOrUpdate(card);
	}

	@DeleteMapping("/deletebyid/{id}")
	public void deleteById(@PathVariable("id") String id) {
		service.deleteById(id);
	}

	@DeleteMapping("/deleteall")
	public void deleteAllPerson() {
		service.deleteAll();
	}
}
