package com.reignleif.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class MapManager {
	
	private Map currentMap;
	private World world;
	
	private OrthogonalTiledMapRenderer mapRenderer;
	private Box2DDebugRenderer debugRenderer;
	
	public MapManager() {
		world = new World(new Vector2(0, 0), true);
		currentMap = new Map(new TmxMapLoader().load("test.tmx"));
		mapRenderer = new OrthogonalTiledMapRenderer(currentMap.getTiledMap());
		debugRenderer = new Box2DDebugRenderer();
		
		createObjectBodies();
	}
	
	public void setMap(Map map) {
		this.currentMap = map;
		mapRenderer.setMap(currentMap.getTiledMap());
		createObjectBodies();
	}
	
	public void render(OrthographicCamera camera) {
		
		mapRenderer.setView(camera);
		mapRenderer.render();
		
		debugRenderer.render(world, camera.combined);
		
	}
	
	public void update(float delta) {
		world.step(1 / 60f, 6, 2);
	}
	
	private void createObjectBodies() {
		BodyDef bDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fDef  = new FixtureDef();
		Body body;
		for (MapObject obj : currentMap.getCollision()) { 
			Rectangle rect = ((RectangleMapObject) obj).getRectangle();
			
			bDef.type = BodyType.StaticBody;
			bDef.position.set((rect.getX() + rect.getWidth() / 2), (rect.getY() + rect.getHeight() / 2));
			body = world.createBody(bDef);
			
			shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
			fDef.shape = shape;
			body.createFixture(fDef);
		}
	}
	
	public World getWorld() {
		return world;
	}
	
	public Map getMap() {
		return currentMap;
	}

}
