package com.reignleif.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractItem {

	protected String itemId;
	protected String itemName;
	protected ItemType itemType;

	public Sprite itemSprite;
	public Vector2 itemPosition;
	public Rectangle itemBounds;

	public static enum ItemType {
		KEY_ITEM("keyItem"), MISC("misc"), POTION("potion"), QUEST_ITEM("questItem");

		String id;

		ItemType(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}
	}

	public AbstractItem(String itemId, ItemType itemType) {
		this.itemId = itemId;
		this.itemType = itemType;

		itemSprite = new Sprite();
		itemPosition = new Vector2(0, 0);
		itemBounds = new Rectangle();
	}

	public abstract void onuse();

	public void setItemSprite(Texture texture) {
		itemSprite.setTexture(texture);
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public void setId(String id) {
		this.itemId = id;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public String getId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public Sprite getItemSprite() {
		return itemSprite;
	}

	public Vector2 getItemPosition() {
		return itemPosition;
	}

	public void setItemPosition(float x, float y) {
		this.itemPosition.x = x;
		this.itemPosition.y = y;
		this.itemSprite.setX(x);
		this.itemSprite.setY(y);
		this.itemBounds.setX(x);
		this.itemBounds.setY(y);
	}

	public void setSprite(Sprite sprite) {
		this.itemSprite = sprite;
		this.itemBounds.set(itemSprite.getX(), itemSprite.getY(), itemSprite.getWidth(), itemSprite.getHeight());
	}
	

	@Override
	public String toString() {
		return getId();
	}
}
