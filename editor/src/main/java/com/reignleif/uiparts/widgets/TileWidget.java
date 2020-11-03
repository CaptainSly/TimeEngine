package com.reignleif.uiparts.widgets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kotcrab.vis.ui.widget.VisImage;

public class TileWidget extends VisImage {

	private TextureRegion tile;
	private TextureRegionDrawable tileDrawable;
	
	public TileWidget(TextureRegion tile) {
		this.tile = tile;
		tileDrawable = new TextureRegionDrawable(tile);
		setDrawable(tileDrawable);
	}

	public TextureRegion getTile() {
		return tile;
	}
	

}
