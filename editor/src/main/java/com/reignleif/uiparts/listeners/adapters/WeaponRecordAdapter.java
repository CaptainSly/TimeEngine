package com.reignleif.uiparts.listeners.adapters;

import java.util.Comparator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.adapter.ArrayAdapter;
import com.kotcrab.vis.ui.util.adapter.ListSelectionAdapter;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.reignleif.weapons.Weapon;

public class WeaponRecordAdapter extends ArrayAdapter<Weapon, VisTable> {

	private final Drawable bg = VisUI.getSkin().getDrawable("window-bg");
	private final Drawable selection = VisUI.getSkin().getDrawable("list-selection");
	private Drawable weaponType;

	public WeaponRecordAdapter(Array<Weapon> array) {
		super(array);

		setSelectionMode(SelectionMode.SINGLE);
		setItemsSorter(new Comparator<Weapon>() {

			@Override
			public int compare(Weapon o1, Weapon o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});

		getSelectionManager().setListener(new ListSelectionAdapter<Weapon, VisTable>() {

			@Override
			public void selected(Weapon item, VisTable view) {
				super.selected(item, view);
				System.out.println("Selected Weapon ID: " + item.getId());
			};
		});

	}

	@Override
	protected void selectView(VisTable view) {
		view.setBackground(selection);
	}

	@Override
	protected void deselectView(VisTable view) {
		view.setBackground(bg);
	}

	@Override
	protected VisTable createView(Weapon item) {
		switch (item.getWeaponType()) {
		case AXE:
			weaponType = new TextureRegionDrawable(new Texture("weaponType"));
			break;
		case SWORD:
			weaponType = new TextureRegionDrawable(new Texture("weaponType"));
			break;
		default:
			weaponType = new TextureRegionDrawable(new Texture("weaponType"));
			break;

		}
		
		VisLabel label = new VisLabel(item.getId());
		VisImage weaponTypeImg = new VisImage(weaponType);
		VisTable table = new VisTable();
		
		table.left();
		table.add(weaponTypeImg, label);
		return table;
	}

}
