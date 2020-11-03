package com.reignleif.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Entity extends Object {

	public Sprite sprite;
	public Rectangle rectangle;
	public Vector2 currentPosition;
	
	public World world;
	
	public enum EntityState {
		IDLE, WALKING, RUNNING, INTERACTING, ATTACKING
	}
	
	public enum EntityDirection {
		UP("up"), DOWN("down"), LEFT("left"), RIGHT("right");
		
		String id;
		
		EntityDirection(String id) {
			this.id = id;
		}
		
		public String getId() {
			return id;
		}
	}

	public Entity(World world) {
		sprite = new Sprite();
		rectangle = new Rectangle();
		currentPosition = new Vector2(0, 0);
		this.world = world;
	}
	

	public Sprite getSprite() {
		return sprite;
	}

	public Vector2 getCurrentPosition() {
		return currentPosition;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
		this.rectangle.setWidth(sprite.getWidth());
		this.rectangle.setHeight(sprite.getHeight());
	}

	public void setCurrentPosition(Vector2 currentPosition) {
		this.currentPosition = currentPosition;

		float x = currentPosition.x, y = currentPosition.y;
		this.sprite.setX(x);
		this.sprite.setY(y);
		this.rectangle.setX(x);
		this.rectangle.setY(y);
	}

	public void setCurrentPosition(float x, float y) {
		this.currentPosition.x = x;
		this.currentPosition.y = y;
		this.sprite.setX(x);
		this.sprite.setY(y);
		this.rectangle.setX(x);
		this.rectangle.setY(y);
	}

	public void moveEntity(float x, float y) {
		this.currentPosition.x += x;
		this.currentPosition.y += y;
		this.sprite.setX(this.sprite.getX() + x);
		this.sprite.setY(this.sprite.getY() + y);
		this.rectangle.x += x;
		this.rectangle.y += y;
	}
	
	public void render(SpriteBatch batch, OrthographicCamera camera) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(sprite, currentPosition.x, currentPosition.y);
		batch.end();
	}

	public abstract boolean onCollide(Entity entity);

}
