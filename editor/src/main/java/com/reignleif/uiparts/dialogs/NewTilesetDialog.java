package com.reignleif.uiparts.dialogs;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.Validators.IntegerValidator;
import com.kotcrab.vis.ui.widget.ButtonBar;
import com.kotcrab.vis.ui.widget.ButtonBar.ButtonType;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooser.Mode;
import com.kotcrab.vis.ui.widget.file.FileChooser.SelectionMode;
import com.kotcrab.vis.ui.widget.file.FileChooserAdapter;
import com.kotcrab.vis.ui.widget.file.FileTypeFilter;
import com.reignleif.io.DataHandler;
import com.reignleif.uiparts.listeners.TilesetDialogListener;

public class NewTilesetDialog extends VisWindow {

	private VisLabel tilesetPathLbl;
	private VisTextButton chooseTilesetBtn, cancelBtn, okBtn;
	private VisValidatableTextField tilesetPixelWidth, tilesetPixelHeight;
	private TilesetDialogListener listener;
	private Texture tex;

	public NewTilesetDialog(TilesetDialogListener listener) {
		super("Add new Tileset");
		this.listener = listener;

		TableUtils.setSpacingDefaults(this);

		addCloseButton();
		closeOnEscape();

		okBtn = new VisTextButton("Okay");
		cancelBtn = new VisTextButton("Cancel");

		ButtonBar buttonBar = new ButtonBar();
		buttonBar.setIgnoreSpacing(true);
		buttonBar.setButton(ButtonType.CANCEL, cancelBtn);
		buttonBar.setButton(ButtonType.OK, okBtn);

		VisTable fieldTable = new VisTable(true);

		tilesetPathLbl = new VisLabel();
		chooseTilesetBtn = new VisTextButton("Choose File");
		tilesetPixelWidth = new VisValidatableTextField(new IntegerValidator());
		tilesetPixelHeight = new VisValidatableTextField(new IntegerValidator());
		
		fieldTable.add(tilesetPathLbl).spaceRight(3);
		fieldTable.add(chooseTilesetBtn).row();
		fieldTable.add(tilesetPixelWidth).spaceRight(3);
		fieldTable.add(tilesetPixelHeight).padLeft(3);

		add(fieldTable).padTop(3).spaceBottom(4);
		row();
		add(buttonBar.createTable()).padBottom(3);

		addListeners();
		pack();
		centerWindow();

	}

	@Override
	protected void close() {
		super.close();
		listener.canceled();
	}

	@Override
	protected void setStage(Stage stage) {
		super.setStage(stage);
		if (stage != null) tilesetPixelWidth.focusField();
	}

	private void addListeners() {

		okBtn.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				listener.finished(tex,
						Integer.parseInt(tilesetPixelHeight.getText().toString()), Integer.parseInt(tilesetPixelHeight.getText().toString()));
				fadeOut();
			}

		});

		cancelBtn.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				close();
			}
		});

		chooseTilesetBtn.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO: Open file chooser
				// Look inside the
				
				System.out.println("Adding image");
				FileTypeFilter typeFilter = new FileTypeFilter(true);
				typeFilter.addRule("Image files (*.png, *.jpg, *.gif)", "png", "jpg", "gif");
				
				FileChooser fileChooser = DataHandler.OPEN_FILECHOOSER;
				fileChooser.setSelectionMode(SelectionMode.FILES);
//				fileChooser.setDirectory(currentProject.getProjectDir());
				fileChooser.setMultiSelectionEnabled(false);			
				fileChooser.setFileTypeFilter(typeFilter);
				fileChooser.setListener(new FileChooserAdapter() {

					@Override
					public void selected(Array<FileHandle> files) {
						super.selected(files);
						if (files.get(0).exists()) {
							tex = new Texture(files.get(0));
							System.out.println("File was: " + files.get(0).name());
							tilesetPathLbl.setText(files.get(0).nameWithoutExtension());

						}
					}

				});
				
				getStage().addActor(fileChooser.fadeIn());
			}
		});

	}

}
