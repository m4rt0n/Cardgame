package com.app.Cardgame;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService implements IPictureService {
	@Autowired
	private PictureRepository pRepo;

	public Picture findById(String id) throws PictureNotFoundException {
		return pRepo.findById(id).orElseThrow(() -> new PictureNotFoundException(id));
	}

	@Override
	public Picture findByTitle(String title) throws PictureNotFoundException {
		List<Picture> pList = pRepo.findAll();
		Optional<Picture> foundPicture = pList.stream().filter(p -> p.getTitle().equals(title)).findFirst();
		return foundPicture.orElseThrow(() -> new PictureNotFoundException(title));

	}

	@Override
	public String save(Picture p) throws IOException {
		pRepo.insert(p);
		return String.format("picture saved: %s", p.getTitle());
	}

}
