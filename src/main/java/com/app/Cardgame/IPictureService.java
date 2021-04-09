package com.app.Cardgame;

import java.io.IOException;

public interface IPictureService {

	Picture findById(String id) throws PictureNotFoundException;

	Picture findByTitle(String title) throws PictureNotFoundException;

	String save(Picture p) throws IOException;

}
