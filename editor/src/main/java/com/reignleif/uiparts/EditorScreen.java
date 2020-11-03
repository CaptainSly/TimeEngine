package com.reignleif.uiparts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPane;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPaneAdapter;
import com.reignleif.Editor;
import com.reignleif.uiparts.tabs.ItemTab;
import com.reignleif.uiparts.tabs.MapTab;
import com.reignleif.uiparts.tabs.WeaponTab;

public class EditorScreen implements Screen {

	private Editor editor;
	private Stage stage;
	
	// Editor Ui Nodes
	
	private VisWindow editorTabWindow;
	private TabbedPane editorTabPane;
	private VisTable container;
	
	
	public EditorScreen(Editor editor) {
		this.editor = editor;
	}

	@Override
	public void show() {
		VisUI.load();
		
		ItemTab itemTab = new ItemTab();
		WeaponTab weaponTab = new WeaponTab();
		MapTab mapTab = new MapTab();
		
		stage = new Stage();
		editorTabWindow = new VisWindow("");
		container = new VisTable();
		TableUtils.setSpacingDefaults(editorTabWindow);
		editorTabPane = new TabbedPane();
		
		editorTabPane.addListener(new TabbedPaneAdapter() {
			@Override
			public void switchedTab(Tab tab) {
				super.switchedTab(tab);
				container.clearChildren();
				container.add(tab.getContentTable()).expand().fill();
			}
		});
		
		editorTabPane.add(itemTab);
		editorTabPane.add(weaponTab);
		editorTabPane.add(mapTab);
		
		
		editorTabPane.switchTab(itemTab);

		editorTabWindow.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		editorTabWindow.add(editorTabPane.getTable()).fillX().expandX();
		editorTabWindow.row();
		editorTabWindow.add(container).expand().fill();
		
		stage.addActor(editorTabWindow);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	public Editor getEditor() {
		return editor;
	}

}
