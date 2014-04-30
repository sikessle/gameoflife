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

	public LoadGameCommand(TextView textUi) {
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
		boolean successfullyLoaded = controller.loadGame(gameToLoad);

		if (successfullyLoaded) {
			ui.addLineToHeaderOutput("Game loaded: " + gameToLoad);
		} else {
			ui.addLineToHeaderOutput("Failed to load game: " + gameToLoad);
		}
		ui.redraw();
	}

	private void parseArguments() {
		if (getArguments().size() < 1) {
			return;
		}

		Iterator<String> iterator = getArguments().iterator();
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
