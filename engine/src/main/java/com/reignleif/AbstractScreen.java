package com.reignleif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class AbstractScreen implements Screen {

	public OrthographicCamera camera;
	public FitViewport viewport;
	
	public static final int WORLD_WIDTH = 240,  WORLD_HEIGHT = 160;
	
	@Override
	public void show() {
		camera = new OrthographicCamera();
		viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		camera.update();
		update(delta);
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
	
	public abstract void update(float delta);
	
	
}
