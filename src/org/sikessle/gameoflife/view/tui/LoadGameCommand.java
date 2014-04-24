package org.sikessle.gameoflife.view.tui;

import java.util.Iterator;

import org.sikessle.gameoflife.controller.GridController;

public class LoadGameCommand extends Command {

	private final GridController controller;
	private String gameToLoad;
	private final TextView ui;
	private static final String KEY = "load";
	private static final String DESCRIPTION = KEY
			+ " [game]: load a saved game";

	public LoadGameCommand(TextView ui) {
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
		ui.addLineToHeaderOutput("Game loaded: " + gameToLoad);
		controller.loadGame(gameToLoad);
	}

	private void parseArguments() {
		if (arguments.size() < 1) {
			return;
		}

		Iterator<String> iterator = arguments.iterator();
		gameToLoad = iterator.next();
	}

	private boolean argsAreValid() {
		return gameToLoad != null;
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}

}
