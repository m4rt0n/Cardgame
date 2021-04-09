package com.app.Cardgame;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureService implements IPictureService {
	@Autowired
	private PictureRepository pRepo;

	public Picture findById(String id) throws PictureNotFoundException {
		return pRepo.findById(id).orElseThrow(() -> new PictureNotFoundException(id));
	}

	public String saveOrUpdate(String title, MultipartFile file) throws IOException {
		Picture p = new Picture(title);
		p.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
		p = pRepo.insert(p);
		return p.getId();
	}
}
