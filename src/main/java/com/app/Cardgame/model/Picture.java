package com.app.Cardgame.model;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pictures")
public class Picture {

	private String title;
	private Binary image;

	public Picture() {

	}

	public Picture(String title, Binary image) {
		this.title = title;
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Binary getImage() {
		return image;
	}

	public void setImage(Binary image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Picture [title=" + title + ", image=" + image + "]";
	}
}
