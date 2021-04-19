package com.app.Cardgame.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pictures")
public class Picture {

	private String title;
	private byte[] image;

	public Picture() {

	}

	public Picture(String title, byte[] image) {
		this.title = title;
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Picture [title=" + title + ", image=" + image + "]";
	}
}
