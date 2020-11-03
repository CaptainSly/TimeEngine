package com.reignleif;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.reignleif.io.FileManager;
import com.reignleif.uiparts.EditorScreen;

public class Editor extends Game {

	private SpriteBatch spriteBatch;
	private FileManager fileManager;

	public Editor(FileManager fileManager) {
		this.fileManager = fileManager;
	}
	
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		FileChooser.setDefaultPrefsName("com.reignleif.filechooser");
		setScreen(new EditorScreen(this));
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}
	
	public SpriteBatch getSBatch() {
		return spriteBatch;
	}
	
	
}
