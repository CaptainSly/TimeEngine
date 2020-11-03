package com.reignleif.map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;

public class Map {

	private TiledMap tiledMap;
	private Array<MapObject> warps, spawns, collision, gameObjects;

	public Map(TiledMap tiledMap) {
		this.tiledMap = tiledMap;

		warps = new Array<MapObject>();
		spawns = new Array<MapObject>();
		collision = new Array<MapObject>();
		gameObjects = new Array<MapObject>();

		fillArraysWithObjects();

	}

	private void fillArraysWithObjects() {
		addObjectsToList(warps, tiledMap.getLayers().get("warpLayer").getObjects());
		addObjectsToList(spawns, tiledMap.getLayers().get("spawnLayer").getObjects());
		addObjectsToList(collision, tiledMap.getLayers().get("collisionLayer").getObjects());
		addObjectsToList(gameObjects, tiledMap.getLayers().get("objectLayer").getObjects());
	}

	private void addObjectsToList(Array<MapObject> array, MapObjects objects) {
		for (MapObject obj : objects)
			array.add(obj);
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}

	public Array<MapObject> getWarps() {
		return warps;
	}

	public Array<MapObject> getSpawns() {
		return spawns;
	}

	public Array<MapObject> getCollision() {
		return collision;
	}

	public Array<MapObject> getGameObjects() {
		return gameObjects;
	}

}
