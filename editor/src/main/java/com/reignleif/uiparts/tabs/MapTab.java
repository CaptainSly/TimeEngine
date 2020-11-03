package com.reignleif.uiparts.tabs;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.reignleif.uiparts.windows.MapWindow;

public class MapTab extends Tab {

	private VisTable table;
	private MapWindow mapWindow;
	
	public MapTab() {
		super(true, false);
		table = new VisTable();
		mapWindow = new MapWindow(this);
		
		table.add(mapWindow).fill().expand();
	}
	
	@Override
	public String getTabTitle() {
		return "Map Tab";
	}

	@Override
	public Table getContentTable() {
		return table;
	}

}
