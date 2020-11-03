package com.reignleif.items;

public class Item extends AbstractItem {

	
	public Item(String itemId, ItemType itemType) {
		super(itemId, itemType);
	}

	@Override
	public void onuse() {
		System.out.println("QUACK");
	}

	
}
