package com.app.Cardgame;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ICardService {

	List<Card> findAll();

	List<Picture> findAllPictures();

	Card findById(String id) throws CardNotFoundException;

	Card findByEnglish(String english) throws CardNotFoundException;

	Card findBySpanish(String spanish) throws CardNotFoundException;

	Card saveOrUpdate(String english, String spanish, MultipartFile file) throws IOException;

	void deleteById(String id);

	void deleteAll();

}
