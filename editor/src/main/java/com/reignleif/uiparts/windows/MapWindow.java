package com.reignleif.uiparts.windows;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.layout.GridGroup;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisSplitPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.reignleif.uiparts.dialogs.NewTilesetDialog;
import com.reignleif.uiparts.listeners.TilesetDialogListener;
import com.reignleif.uiparts.widgets.TileWidget;

public class MapWindow extends VisWindow {

	private TiledMap currentMap = null;

	private ListView<MapLayers> layerList;
	private ListView<TiledMap> mapList;
	private ListView<MapObjects> objectList;
	private ListView<MapProperties> mapPropertiesList;

	private GridGroup tileBtnGridGroup;

	private VisTable mapListView;
	private VisTable tilesetView;
	private VisTable propertyView;
	private VisTable brushView;
	private VisTable mapView;
	private VisTable objectView;
	private VisTable layerView;

	private VisTextButton addTilesetBtn;

	private Array<MapLayers> mapLayerArray;
	private Array<TiledMap> mapArray;
	private Array<MapObjects> mapObjectArray;
	private Array<MapProperties> mapPropertiesArray;

	private TextureRegion currentTile;

	private Tab parentTab;

	private TextureRegion[][] tileset;

	public MapWindow(Tab tab) {
		super("Map Window");
		this.parentTab = tab;

		mapView = new VisTable();
		tilesetView = new VisTable();
		propertyView = new VisTable();
		brushView = new VisTable();
		mapListView = new VisTable();
		objectView = new VisTable();

		tileBtnGridGroup = new GridGroup();
		tileBtnGridGroup.setSpacing(1f);
		tileBtnGridGroup.setItemWidth(64);
		tileBtnGridGroup.setItemHeight(64);

		mapLayerArray = new Array<MapLayers>();
		mapArray = new Array<TiledMap>();
		mapObjectArray = new Array<MapObjects>();
		mapPropertiesArray = new Array<MapProperties>();

		addTilesetBtn = new VisTextButton("Create Tileset");
		addTilesetBtn.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				getStage().addActor(new NewTilesetDialog(new TilesetDialogListener() {

					@Override
					public void finished(Texture texture, int tileW, int tileH) {
						tileset = TextureRegion.split(texture, tileW, tileH);

						for (int x = 0; x < tileset.length; x++) {
							for (int y = 0; y < tileset[x].length; y++) {

								TextureRegion tex = tileset[x][y];
								TileWidget tile = new TileWidget(tex);
								tile.addListener(new ClickListener() {
									
									public void clicked(InputEvent event, float x, float y) {
										System.out.println("click");										
									};
								});

								tileBtnGridGroup.addActor(tile);
							}
						}

					}

					@Override
					public void canceled() {
					}
					
				}).fadeIn());

			}

		});

		VisScrollPane scroll = new VisScrollPane(tileBtnGridGroup);
		scroll.setScrollingDisabled(true, false);

		tilesetView.top().left();
		tilesetView.add(addTilesetBtn).row();
		tilesetView.add(scroll).expand().fill();

		VisSplitPane mainWindowSplit, secondarySplit, layerSplit, propertiesSplit, mapSplit, brushSplit;

		mainWindowSplit = new VisSplitPane(tilesetView, mapView, false);
		mainWindowSplit.setMaxSplitAmount(0.3f);
		mainWindowSplit.setMinSplitAmount(0.2f);
		mainWindowSplit.setSplitAmount(0.3f);

		add().row(); // TODO: Add in bar that lets you create and delete maps
		add(mainWindowSplit).expand().fill();

	}

}
