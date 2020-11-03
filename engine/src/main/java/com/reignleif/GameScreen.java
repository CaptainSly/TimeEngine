package com.reignleif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.reignleif.entity.Npc;
import com.reignleif.entity.Player;
import com.reignleif.items.Item;
import com.reignleif.map.MapManager;

public class GameScreen extends AbstractScreen {

	private TimeGame time;
	private Player player;
	private Item item;
	private Npc npc;

	private MapManager mapManager;

	public GameScreen(TimeGame time) {
		this.time = time;
	}

	@Override
	public void show() {
		super.show();

		mapManager = new MapManager();
		player = new Player(mapManager.getWorld());

		Texture texture = new Texture(Gdx.files.internal("item_9.png"));
		Texture texture2 = new Texture(Gdx.files.internal("item_3.png"));

		camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

		player.setCurrentPosition(100, 100);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();

		mapManager.render(camera);
		player.render(time.getSBatch(), camera);

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void update(float delta) {

		mapManager.update(delta);

camera.position.x = player.getPlayerBody().getPosition().x;
		camera.position.y = player.getPlayerBody().getPosition().y;
		
		player.update(delta);
	}

}
