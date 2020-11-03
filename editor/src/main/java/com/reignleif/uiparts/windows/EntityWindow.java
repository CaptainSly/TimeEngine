package com.reignleif.uiparts.windows;

import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.reignleif.entity.Entity;

public class EntityWindow extends VisWindow {

	private Array<Entity> entityRecordList;
	private VisTable recordContentView;
	
	private Entity current;
	
	private Tab parent;
	
	public EntityWindow(Tab tab) {
		super("Entity Window" );
		this.parent = tab;
		
		add(recordContentView);
	}

}
