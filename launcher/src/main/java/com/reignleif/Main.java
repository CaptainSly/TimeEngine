package com.reignleif;

import com.reignleif.ui.Time;

import javafx.application.Application;

public class Main {

	public static void main(String[] args) {

		if (args != null) {
			for (String s : args) {
				if (s != null) {
					if (s.equals("-e")) { // Editor
						Time.setupLwjglConfig(false);
						Time.launchEditor();
					} else if (s.equals("-g")) { // Game
						Time.setupLwjglConfig(false);
						Time.launchGame();
					}
				}
			}
		} else
			Application.launch(Time.class, args);
	}

}
