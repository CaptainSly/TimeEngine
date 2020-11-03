package com.reignleif.data;

import com.badlogic.gdx.utils.Array;
import com.reignleif.items.Item;

public class Lootlist {
	
	private Array<Item> loot;

	public Lootlist(Array<Item> loot) {
		this.loot = loot;
	}
	
	public Array<Item> getLootlist() {
		return loot;
	}
	
}
