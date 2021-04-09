package com.app.Cardgame;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IPictureService {

	Picture findById(String id) throws PictureNotFoundException;

	String saveOrUpdate(String title, MultipartFile image) throws IOException;

}
