package com.app.Cardgame;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/pictures")
public class PictureController {
	@Autowired
	private IPictureService pService;

	@GetMapping("/hello")
	public @ResponseBody String hello() {
		return "hello";
	}

	/*
	 * @GetMapping("/getbyid/{id}") public String getPhoto(@PathVariable String id,
	 * Model model) throws PictureNotFoundException { Picture photo =
	 * pService.findById(id); model.addAttribute("title", photo.getTitle());
	 * model.addAttribute("image",
	 * Base64.getEncoder().encodeToString(photo.getImage().getData())); return
	 * "photos"; }
	 */
	// ????
	@PostMapping("/upload")
	public String uploadPhoto(@RequestParam(value = "image") MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		Picture p = new Picture();
		p.setTitle(fileName);
		p.setImage(new Binary(file.getBytes()));
		return pService.save(p);
	}

	@PostMapping("/add")
	public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model)
			throws IOException {
		Picture p = new Picture();
		p.setTitle(title);
		p.setImage(new Binary(image.getBytes()));
		return pService.save(p);
	}

	@GetMapping("/getbyid/{id}")
	public String getPhotoById(@PathVariable("id") String id) throws PictureNotFoundException {
		Picture p = pService.findById(id);
		Encoder encoder = Base64.getEncoder();
		String data = encoder.encodeToString(p.getImage().getData());
		return String.format("title: %s, id: %s, data: %s", p.getTitle(), p.getId(), data);
	}

	@GetMapping("/getbytitle/{title}")
	public String getPhotoByTitle(@PathVariable("title") String title) throws PictureNotFoundException {
		Picture p = pService.findByTitle(title);
		Encoder encoder = Base64.getEncoder();
		String data = encoder.encodeToString(p.getImage().getData());
		return String.format("title: %s, id: %s, data: %s", p.getTitle(), p.getId(), data);
	}
}
