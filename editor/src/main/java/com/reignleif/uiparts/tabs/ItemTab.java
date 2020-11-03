package com.reignleif.uiparts.tabs;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.reignleif.uiparts.windows.ItemWindow;

public class ItemTab extends Tab {

	private VisTable rootTable;
	private ItemWindow itemWindow;
	
	public ItemTab() {
		super(true, false);
		rootTable = new VisTable();
		itemWindow = new ItemWindow(this);
		
		rootTable.add(itemWindow).fill().expand();		
	}
	
	@Override
	public String getTabTitle() {
		return "Item Tab";
	}

	@Override
	public Table getContentTable() {
		return rootTable;
	}

}
