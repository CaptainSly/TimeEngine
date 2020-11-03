package com.reignleif.uiparts.windows;

import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.reignleif.uiparts.listeners.adapters.WeaponRecordAdapter;
import com.reignleif.weapons.Weapon;

public class WeaponsWindow extends VisWindow {

	private Array<Weapon> weaponRecordList;
	private ListView<Weapon> weaponView;
	private VisTable recordContentView;
	
	private Tab parentTab;
	
	private Weapon currentSelectedWeapon;
	
	public WeaponsWindow(Tab tab) {
		super("Weapons Window");
		this.parentTab = tab;
		
		TableUtils.setSpacingDefaults(this);
		columnDefaults(0).left();
		setResizable(false);
		setMovable(false);
		setFillParent(true);
		
		weaponRecordList = new Array<Weapon>();
		recordContentView = new VisTable();
		
		final WeaponRecordAdapter adapter = new  WeaponRecordAdapter(weaponRecordList);
		
	}

}
