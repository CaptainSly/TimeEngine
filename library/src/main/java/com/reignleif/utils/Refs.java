package com.reignleif.utils;

public class Refs {

	public static final String PROJECT_MAPS_FOLDER = "maps/";
	public static final String PROJECT_SCRIPTS_FOLDER = "scripts/";
	public static final String PROJECT_ASSET_FOLDER = "assets/";

	public static final String ENGINE_FOLDER = System.getProperty("user.home") + "/.time/";
	public static final String ENGINE_DATA_DIR = ENGINE_FOLDER + "data/";
	public static final String ENGINE_PROJECT_DIR = ENGINE_FOLDER + "projects/";

	// Category Bits
	public static final short EMPTY_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short OBJ_BIT = 4;
	public static final short ENEMY_BIT = 8;
	public static final short ENTITY_BIT = 16;
	public static final short TERRAIN_BIT = 32;
	
}