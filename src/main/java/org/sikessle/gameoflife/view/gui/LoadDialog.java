package org.sikessle.gameoflife.view.gui;

import javax.swing.JOptionPane;

import org.sikessle.gameoflife.controller.GridController;

public class LoadDialog {

	private static final String TITLE = "Load";
	private static final Object LOAD_TEXT = "Choose the game to load";
	private final GridController controller;

	public LoadDialog(GridController controller) {
		if (controller == null) {
			throw new IllegalArgumentException();
		}
		this.controller = controller;
		showDialog();
	}

	private void showDialog() {
		String[] games = controller.listGames().toArray(new String[1]);

		String gameToLoad = (String) JOptionPane.showInputDialog(null,
				LOAD_TEXT, TITLE, JOptionPane.PLAIN_MESSAGE, null, games, 0);

		if (gameToLoad != null) {
			controller.loadGame(gameToLoad);
		}
	}
}
