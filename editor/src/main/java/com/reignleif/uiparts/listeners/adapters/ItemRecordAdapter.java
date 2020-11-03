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
import com.reignleif.items.Item;

public class ItemRecordAdapter extends ArrayAdapter<Item, VisTable> {

	private final Drawable bg = VisUI.getSkin().getDrawable("window-bg");
	private final Drawable selection = VisUI.getSkin().getDrawable("list-selection");
	private Drawable itemType;

	public ItemRecordAdapter(Array<Item> array) {
		super(array);
		
		setSelectionMode(SelectionMode.SINGLE);
		setItemsSorter(new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});

		getSelectionManager().setListener(new ListSelectionAdapter<Item, VisTable>() {

			@Override
			public void selected(Item item, VisTable view) {
				super.selected(item, view);
				System.out.println("Selected Item ID: " + item.toString());
			}
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
	protected VisTable createView(Item item) {

		switch (item.getItemType()) {
		case POTION:
			// Set itemType drawable to Potion
			itemType = new TextureRegionDrawable(new Texture("potionType.png"));
			break;
		case KEY_ITEM:
			// Set itemType drawable to KeyItem
			itemType = new TextureRegionDrawable(new Texture("keyItemType.png"));
			break;
		case MISC:
			// Set itemType drawable to Misc
			itemType = new TextureRegionDrawable(new Texture("miscType.png"));
			break;
		case QUEST_ITEM:
			// Set itemType drawable to QuestItem
			itemType = new TextureRegionDrawable(new Texture("questItemType.png"));
			break;
		default:
			// Set itemType drawable to Misc
			itemType = new TextureRegionDrawable(new Texture("miscType.png"));
			break;

		}

		VisLabel label = new VisLabel(item.getId());
		VisImage itemTypeImg = new VisImage(itemType);
		VisTable table = new VisTable();

		table.left();
		table.add(itemTypeImg, label);
		return table;
	}

}
