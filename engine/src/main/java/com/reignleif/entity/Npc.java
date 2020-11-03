package com.reignleif.entity;

import com.badlogic.gdx.physics.box2d.World;

public class Npc extends Entity {
	
	public Npc(World world) {
		super(world);
	}
	
	@Override
	public boolean onCollide(Entity entity) {
		return false;
	}

}
