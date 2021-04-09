package com.app.Cardgame;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/pictures")
public class PictureController {
	@Autowired
	private IPictureService pService;

	@GetMapping("/getbyid/{id}")
	public String getPhoto(@PathVariable String id, Model model) throws PictureNotFoundException {
		Picture photo = pService.findById(id);
		model.addAttribute("title", photo.getTitle());
		model.addAttribute("image", Base64.getEncoder().encodeToString(photo.getImage().getData()));
		return "photos";
	}

	@GetMapping("//upload")
	public String uploadPhoto(Model model) {
		model.addAttribute("message", "hello");
		return "uploadPicture";
	}

	@PostMapping("/add")
	public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model)
			throws IOException {
		String id = pService.saveOrUpdate(title, image);
		return "redirect:/pictures/" + id;
	}
}
