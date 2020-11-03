package com.reignleif.ui;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.reignleif.Editor;
import com.reignleif.TimeGame;
import com.reignleif.io.FileManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Time extends Application {

	private static int width = 1280, height = 800;
	private static String title = "Time Engine";
	private boolean keepLauncherOpenB = false;

	private static LwjglApplicationConfiguration config;

	private Stage stage;

	private BorderPane root;
	private GridPane grid;

	private Button launchSettingsBtn, launchEditorBtn, launchGameFileBtn, launchGameBtn;
	private CheckBox keepLauncherOpen;
	
	private static FileManager fileManager;

	public Time() {
	}

	@Override
	public void init() throws Exception {
		super.init();
		// Instantiate File Manager and what not
		fileManager = new FileManager();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		root = new BorderPane();
		grid = new GridPane();

		root.setCenter(grid);
		setupButtons();
		setupGrid();

		setupLwjglConfig(false);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void setupLwjglConfig(boolean resize) {
		config = new LwjglApplicationConfiguration();
		config.title = title;
		config.width = width;
		config.height = height;
		config.resizable = resize;
	}

	public static LwjglApplication launchGame() {
		return new LwjglApplication(new TimeGame(fileManager), config);
	}

	public static LwjglApplication launchEditor() {
		return new LwjglApplication(new Editor(fileManager), config);
	}

	private void setupButtons() {
		launchSettingsBtn = new Button();
		launchEditorBtn = new Button();
		launchGameBtn = new Button();
		launchGameFileBtn = new Button();

		keepLauncherOpen = new CheckBox();

		keepLauncherOpen.setText("Keep Launcher Open");
		keepLauncherOpen.selectedProperty().addListener((o, ov, nv) -> {
			keepLauncherOpenB = nv;
		});

		launchSettingsBtn.setText("Settings");
		launchSettingsBtn.setOnAction(e -> {
			// Open Settings Ui
		});

		launchEditorBtn.setText("Editor");
		launchEditorBtn.setOnAction(e -> {
			// Launch the Editor and close the launcher
			System.out.println("Opening Editor");
			// Make sure to pass the most up to date settings to editor before launching
			launchEditor();
			if (!keepLauncherOpenB) stage.close();
		});

		launchGameBtn.setText("Play Game");
		launchGameBtn.setOnAction(e -> {
			// Launch the Game and Close the launcher
			System.out.println("Launching Game");
			// Make Sure to pass the most up to date settings to engine before launching
			launchGame();
			if (!keepLauncherOpenB) stage.close();
		});

		launchGameFileBtn.setText("Game Files");
		launchGameFileBtn.setOnAction(e -> {
			// Open the Game Files Ui
		});

	}

	private void setupGrid() {
		grid.add(launchEditorBtn, 0, 0);
		grid.add(launchGameBtn, 0, 1);
		grid.add(launchSettingsBtn, 0, 2);
		grid.add(launchGameFileBtn, 0, 3);
		grid.add(keepLauncherOpen, 0, 4);

	}

}
