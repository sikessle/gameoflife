package org.sikessle.gameoflife.view.gui;

import javax.swing.JOptionPane;

import org.sikessle.gameoflife.controller.GridController;

public class SaveDialog {

	private static final Object SAVE_TEXT = "Save game under name:";
	private static final String SAVE_TITLE = "Save game";
	private static final Object CONFIRM_TEXT = "Game saved";
	private static final Object ERROR_TEXT = "Failed to save game";
	private final GridController controller;

	public SaveDialog(GridController controller) {
		if (controller == null) {
			throw new IllegalArgumentException();
		}
		this.controller = controller;
		showDialog();
	}

	private void showDialog() {
		String gameName = JOptionPane.showInputDialog(null, SAVE_TEXT,
				SAVE_TITLE, JOptionPane.QUESTION_MESSAGE);

		if (gameName != null) {
			saveGameAndShowConfirmation(gameName);
		}
	}

	private void saveGameAndShowConfirmation(String gameName) {
		boolean isSaved = controller.saveGame(gameName);

		if (isSaved) {
			showConfirmationDialog();
		} else {
			showErrorDialog();
		}
	}

	private void showConfirmationDialog() {
		JOptionPane.showMessageDialog(null, CONFIRM_TEXT);
	}

	private void showErrorDialog() {
		JOptionPane.showMessageDialog(null, ERROR_TEXT);
	}

}
