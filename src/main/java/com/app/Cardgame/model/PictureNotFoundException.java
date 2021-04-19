package com.app.Cardgame.model;

public class PictureNotFoundException extends Exception {
	public PictureNotFoundException(String param) {
		super(String.format("picture is not found: %s", param));
	}
}
