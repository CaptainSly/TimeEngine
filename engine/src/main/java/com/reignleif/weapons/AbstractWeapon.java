package com.reignleif.weapons;

import com.reignleif.items.Item;

public abstract class AbstractWeapon extends Item {

	protected WeaponType weaponType;
	
	public static enum WeaponType {
		
		SWORD("sword"), AXE("axe");
		
		String id;
		
		WeaponType(String id) {
			this.id = id;
		}
		
		public String getId() {
			return id;
		}
		
	}
	
	public AbstractWeapon(String itemId, ItemType itemType) {
		super(itemId, itemType);
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}

}
