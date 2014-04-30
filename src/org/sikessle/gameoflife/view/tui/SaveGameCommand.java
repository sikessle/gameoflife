package org.sikessle.gameoflife.view.tui;

import java.util.Iterator;

import org.sikessle.gameoflife.controller.GridController;

public class SaveGameCommand extends Command {

	private final GridController controller;
	private String gameName;
	private final TextView ui;
	private static final String KEY = "save";
	private static final String DESCRIPTION = KEY
			+ " [name]: save the game under the name";

	public SaveGameCommand(TextView textUi) {
		if (textUi == null) {
			throw new IllegalArgumentException();
		}
		this.ui = textUi;
		this.controller = textUi.getGridController();
	}

	@Override
	protected boolean isResponsible() {
		parseArguments();
		return getCommand().equals(KEY) && argsAreValid();
	}

	@Override
	protected void execute() {
		boolean successfullySaved = controller.saveGame(gameName);

		if (successfullySaved) {
			ui.addLineToHeaderOutput("Game saved under " + gameName);
		} else {
			ui.addLineToHeaderOutput("Failed to save game under " + gameName);
		}
		ui.redraw();
	}

	private void parseArguments() {
		if (getArguments().size() < 1) {
			return;
		}

		Iterator<String> iterator = getArguments().iterator();
		gameName = iterator.next();
	}

	private boolean argsAreValid() {
		return gameName != null;
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}

}
