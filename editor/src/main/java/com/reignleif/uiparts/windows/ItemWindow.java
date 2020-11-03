package com.reignleif.uiparts.windows;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.util.dialog.Dialogs.OptionDialog;
import com.kotcrab.vis.ui.util.dialog.Dialogs.OptionDialogType;
import com.kotcrab.vis.ui.util.dialog.OptionDialogListener;
import com.kotcrab.vis.ui.widget.ButtonBar;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.ListView.ItemClickListener;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;
import com.kotcrab.vis.ui.widget.Separator;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisSplitPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextArea;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.reignleif.items.AbstractItem.ItemType;
import com.reignleif.items.Item;
import com.reignleif.uiparts.dialogs.ItemDialog;
import com.reignleif.uiparts.listeners.ItemDialogListener;
import com.reignleif.uiparts.listeners.adapters.ItemRecordAdapter;

public class ItemWindow extends VisWindow {

	private Array<Item> itemRecordList;
	private ButtonBar menuBar;
	
	private boolean dirty;

	private ListView<Item> itemView;
	private VisTable recordContentView;

	private Tab parentTab;

	private Item currentSelectedItem;

	public ItemWindow(Tab tab) {
		super("Item Window");
		this.parentTab = tab;

		TableUtils.setSpacingDefaults(this);
		columnDefaults(0).left();
		setResizable(false);
		setMovable(false);
		setFillParent(true);

		menuBar = new ButtonBar();
		itemRecordList = new Array<Item>();
		recordContentView = new VisTable();
		
		final ItemRecordAdapter adapter = new ItemRecordAdapter(itemRecordList);
		itemView = new ListView<Item>(adapter);

		itemView.getMainTable().addListener(new ClickListener(1) {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				PopupMenu rgtClkPM = new PopupMenu();
				MenuItem newItem = new MenuItem("New Item");
				MenuItem deleteItem = new MenuItem("Delete Item");
				MenuItem duplicateItem = new MenuItem("Duplicate Item");

				newItem.addCaptureListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						super.clicked(event, x, y);
						ItemDialog itemDialog = new ItemDialog(new ItemDialogListener() {

							@Override
							public void finished(String input, ItemType itemType) {
								System.out.println("Creating Item with id and name: " + input + " and itemType: "
										+ itemType.getId());
								Item d = new Item(input, itemType);
								itemRecordList.add(d);
								adapter.itemsDataChanged();
							}

							@Override
							public void canceled() {
							}
						});

						getStage().addActor(itemDialog.fadeIn());
					}
				});

				deleteItem.addCaptureListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						super.clicked(event, x, y);

						if (currentSelectedItem != null) {
							OptionDialog d = Dialogs.showOptionDialog(getStage(), "Delete",
									"Are you sure you want to delete: " + currentSelectedItem.getId(),
									OptionDialogType.YES_NO, new OptionDialogListener() {

										@Override
										public void yes() {
											itemRecordList.removeValue(currentSelectedItem, true);
											adapter.itemsDataChanged();
											currentSelectedItem = null;
											recordContentView.clear();
										}

										@Override
										public void no() {}

										@Override
										public void cancel() {
											no();
										}

									});
						}

					}

				});

				duplicateItem.addCaptureListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						super.clicked(event, x, y);
						if (currentSelectedItem != null) {
							Dialogs.showOptionDialog(getStage(), "Duplicate",
									"Duplicate Item: " + currentSelectedItem.getId(), OptionDialogType.YES_NO,
									new OptionDialogListener() {

										@Override
										public void yes() {
											System.out.println("Duplicating item " + currentSelectedItem.getId());
											Item d = new Item(currentSelectedItem.getId() + "-[D]",
													currentSelectedItem.getItemType());
											System.out.println(d.getId() + " | " + d.getItemType().getId());
											itemRecordList.add(d);

											adapter.itemsDataChanged();
										}

										@Override
										public void no() {
										}

										@Override
										public void cancel() {
										}
									});
						}
					}

				});

				rgtClkPM.addItem(newItem);
				rgtClkPM.addItem(deleteItem);
				rgtClkPM.addItem(duplicateItem);

				if (event.getButton() == 1) {
					// Show Pop up menu
					System.out.println("Pop Up Menu");
					rgtClkPM.showMenu(getStage(), x, y);
				}

			}
		});

		itemView.setItemClickListener(new ItemClickListener<Item>() {

			@Override
			public void clicked(Item item) {

				if (currentSelectedItem == item) return;

				if (currentSelectedItem == null) {
					currentSelectedItem = item;
					System.out.println("Setting Current Item to itemId: " + currentSelectedItem.getId());
					recordContentView.clear();
					setupRecordContentView(currentSelectedItem);
				}
				if (currentSelectedItem != item) {
					System.out.println(
							"Switching from itemId: " + currentSelectedItem.getId() + " to itemId: " + item.getId());
					
					// Make sure to ask to save work if need be
					// TODO: Make save dialog

					if (dirty) {
						Dialogs.showOptionDialog(getStage(), "Do you want to save",
								"Would you like to save changes made to Item: " + currentSelectedItem.getId(),
								OptionDialogType.YES_NO_CANCEL, new OptionDialogListener() {

									@Override
									public void yes() {
										// TODO: Save Changes to the item
										
										parentTab.setDirty(false);
										dirty = false;
										currentSelectedItem = item;
										adapter.itemsDataChanged();
										recordContentView.clear();
										setupRecordContentView(currentSelectedItem);
									}

									@Override
									public void no() {
										parentTab.setDirty(false);
										dirty = false;
										currentSelectedItem = item;
										recordContentView.clear();
										setupRecordContentView(currentSelectedItem);
									}

									@Override
									public void cancel() {
										return;
									}
								});
					} else {

						currentSelectedItem = item;
						recordContentView.clear();
						setupRecordContentView(currentSelectedItem);
					}
				}

			}
		});

		VisSplitPane splitPane = new VisSplitPane(itemView.getMainTable(), recordContentView, false);
		splitPane.setSplitAmount(0.2f);
		splitPane.setMaxSplitAmount(0.2f);
		splitPane.setMinSplitAmount(0.19f);
		add(splitPane).fill().expand();

	}

	private void setupRecordContentView(Item item) {
		VisTextField idField = new VisTextField();
		VisTextField nameField = new VisTextField();
		VisTextArea descArea = new VisTextArea();
		VisTextField valueField = new VisTextArea();
		VisTextButton saveButton = new VisTextButton("Save");
		VisSelectBox<ItemType> itemTypeSelBox = new VisSelectBox<ItemType>();
		VisImage spriteImage = new VisImage();
		
		// Drawables
		Texture noSprite = new Texture("blank.png");

		idField.setText(item.getId());
		idField.addCaptureListener(new EventListener() {

			@Override
			public boolean handle(Event event) {
				parentTab.dirty();
				dirty = true;
				return true;
			}
		});

		nameField.setText(item.getItemName());
		nameField.addCaptureListener(new EventListener() {

			@Override
			public boolean handle(Event event) {
				parentTab.dirty();
				dirty = true;
				return true;
			}
		});
		descArea.setPrefRows(5);

		itemTypeSelBox.setItems(ItemType.values());
		itemTypeSelBox.setSelected(item.getItemType());

		if (spriteImage.getDrawable() == null) spriteImage.setDrawable(noSprite);
		
		spriteImage.addCaptureListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				// TODO: Open filebrowser to local editor folder and set the selected sprite
				// image to it
				System.out.println("Clicked Image");
			}

		});

		recordContentView.top().left();
		recordContentView.add(new VisLabel("Item ID:"), idField, new Separator(), new Separator(), spriteImage).row();
		recordContentView.add(new VisLabel("Item Name:"), nameField).row();
		recordContentView.add(new VisLabel("Item Description:"), descArea).row();
		recordContentView.add(new VisLabel("Item Value:"), valueField).row();
		recordContentView.add(new VisLabel("Item Type:"), itemTypeSelBox).row();
		recordContentView.add(saveButton);
	}

	public Tab getParentTab() {
		return parentTab;
	}

}
