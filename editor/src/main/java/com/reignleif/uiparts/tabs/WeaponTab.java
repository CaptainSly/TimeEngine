package com.reignleif.uiparts.tabs;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.reignleif.uiparts.windows.WeaponsWindow;

public class WeaponTab extends Tab {

	private VisTable content;
	private WeaponsWindow weaponWindow;
	
	public WeaponTab() {
		super(true, false);
		content = new VisTable();
		weaponWindow = new WeaponsWindow(this);
		
		
		content.add(weaponWindow).expand().fill();
	
	}
	
	@Override
	public String getTabTitle() {
		return "Weapon Tab";
	}

	@Override
	public Table getContentTable() {
		return content;
	}

}
