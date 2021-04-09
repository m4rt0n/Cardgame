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
@RequestMapping(path = "/cards")
public class CardController {
	@Autowired
	ICardService cService;

	@GetMapping("/hello")
	public @ResponseBody String hello() {
		return "hello";
	}

	@PostMapping("/addcards")
	public void addCards() {
		cService.saveOrUpdate(new Card("dog", "perro"));
		cService.saveOrUpdate(new Card("cat", "gato"));
		cService.saveOrUpdate(new Card("flower", "flor"));
	}

	@GetMapping("/getall")
	public Iterable<Card> getAll() {
		return cService.findAll();
	}

	@GetMapping("/getbyid/{id}")
	public Card getById(@PathVariable("id") String id) throws CardNotFoundException {
		return cService.findById(id);
	}

	@GetMapping("/getbyenglish/{english}")
	public Card getByEnglish(@PathVariable("english") String english) throws CardNotFoundException {
		return cService.findByEnglish(english);
	}

	@GetMapping("/getbyspanish/{spanish}")
	public Card getBySpanish(@PathVariable("spanish") String spanish) throws CardNotFoundException {
		return cService.findByEnglish(spanish);
	}

	@PostMapping("/save")
	public void saveOrUpdate(@RequestBody Card card) {
		cService.saveOrUpdate(card);
	}

	@DeleteMapping("/deletebyid/{id}")
	public void deleteById(@PathVariable("id") String id) {
		cService.deleteById(id);
	}

	@DeleteMapping("/deleteall")
	public void deleteAllPerson() {
		cService.deleteAll();
	}
}
