package com.reignleif;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.reignleif.io.FileManager;

public class TimeGame extends Game {
	
	private SpriteBatch spriteBatch;
	private FileManager fileManager;
	
	public TimeGame(FileManager fileManager) {
		this.fileManager = fileManager;
	}
	
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		setScreen(new GameScreen(this));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		getScreen().dispose();
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}
	
	public SpriteBatch getSBatch() {
		return spriteBatch;
	}

}
