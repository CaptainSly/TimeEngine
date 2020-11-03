package com.reignleif.uiparts.listeners;

import com.reignleif.items.AbstractItem.ItemType;

public interface ItemDialogListener {

	void finished(String input, ItemType itemType);
	
	void canceled();
	
}
