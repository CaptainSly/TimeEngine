package com.reignleif.uiparts.dialogs;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.ButtonBar;
import com.kotcrab.vis.ui.widget.ButtonBar.ButtonType;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.reignleif.items.AbstractItem.ItemType;
import com.reignleif.uiparts.listeners.ItemDialogListener;

public class ItemDialog extends VisWindow {

	private VisTextField inputField;
	private VisSelectBox<ItemType> itemTypeBox;
	private VisTextButton okButton, cancelButton;
	private ItemDialogListener listener;

	public ItemDialog(ItemDialogListener listener) {
		super("Create new Item");
		this.listener = listener;

		TableUtils.setSpacingDefaults(this);

		addCloseButton();
		closeOnEscape();

		ButtonBar buttonBar = new ButtonBar();
		buttonBar.setIgnoreSpacing(true);
		buttonBar.setButton(ButtonType.CANCEL, cancelButton = new VisTextButton("Cancel"));
		buttonBar.setButton(ButtonType.OK, okButton = new VisTextButton(ButtonType.OK.getText()));

		VisTable fieldTable = new VisTable(true);

		inputField = new VisTextField();
		itemTypeBox = new VisSelectBox<ItemType>();

		itemTypeBox.setItems(ItemType.values());

		fieldTable.add(inputField).expand().fill();
		fieldTable.row();
		fieldTable.add(itemTypeBox);

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
		if (stage != null) inputField.focusField();
	}

	public ItemDialog setText(String text) {
		return setText(text, false);
	}

	/**
	 * @param selectText if true text will be selected (this can be useful if you
	 *                   want to allow user quickly erase all text).
	 */
	public ItemDialog setText(String text, boolean selectText) {
		inputField.setText(text);
		inputField.setCursorPosition(text.length());
		if (selectText) {
			inputField.selectAll();
		}

		return this;
	}

	private void addListeners() {
		okButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				listener.finished(inputField.getText(), itemTypeBox.getSelected());
				fadeOut();
			}
		});

		cancelButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				close();
			}
		});

		inputField.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Keys.ENTER && okButton.isDisabled() == false) {
					listener.finished(inputField.getText(), itemTypeBox.getSelected());
					fadeOut();
				}

				return super.keyDown(event, keycode);
			}
		});
	}

}
