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

	public SaveGameCommand(TextView ui) {
		if (ui == null) {
			throw new NullPointerException();
		}
		this.ui = ui;
		this.controller = ui.getGridController();
	}

	@Override
	protected boolean isResponsible() {
		parseArguments();
		return command.equals(KEY) && argsAreValid();
	}

	@Override
	protected void execute() {
		controller.saveGame(gameName);
		ui.addLineToHeaderOutput("Game saved under " + gameName);
		ui.redraw();
	}

	private void parseArguments() {
		if (arguments.size() < 1) {
			return;
		}

		Iterator<String> iterator = arguments.iterator();
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
