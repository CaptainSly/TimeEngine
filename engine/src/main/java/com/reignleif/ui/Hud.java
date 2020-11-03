package com.reignleif.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Hud {

	private Stage stage;

	
	public Hud() {
		stage = new Stage();
	}
	
	public void draw() {
		stage.draw();
	}
	
	public void update(float delta) {
		stage.act(delta);
	}
	
}
