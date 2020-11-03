package com.reignleif.uiparts.listeners;

import com.badlogic.gdx.graphics.Texture;

public interface TilesetDialogListener {

	void finished(Texture texture, int tileW, int tileH);
	
	void canceled();
	
}