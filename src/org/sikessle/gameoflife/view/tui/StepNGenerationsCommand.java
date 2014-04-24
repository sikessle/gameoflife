package org.sikessle.gameoflife.view.tui;

import java.util.Iterator;

import org.sikessle.gameoflife.controller.GridController;

public class StepNGenerationsCommand extends Command {

	private final GridController controller;
	private String command;
	private Args arguments;
	private int frames;
	private static final int DELAY_BETWEEN_FRAMES_MS = 100;
	private static final String DESCRIPTION = "n [x]: step x generations";

	public StepNGenerationsCommand(TextView ui) {
		if (ui == null) {
			throw new NullPointerException();
		}
		this.controller = ui.getGridController();
	}

	@Override
	public void handleIfResponsible(String command, Args arguments) {
		this.command = command;
		this.arguments = arguments;

		parseArguments();

		if (isCorrectCommand()) {
			startAnimation();
		}
	}

	private void parseArguments() {
		if (arguments.size() < 1) {
			return;
		}

		Iterator<String> iterator = arguments.iterator();

		try {
			frames = Integer.parseInt(iterator.next());
		} catch (NumberFormatException e) {
			frames = 0;
		}
	}

	private boolean isCorrectCommand() {
		if (!command.equals("n") || argsNotValid()) {
			return false;
		}

		return true;
	}

	private boolean argsNotValid() {
		return frames <= 0;
	}

	private void startAnimation() {
		for (int i = 0; i < frames; i++) {
			controller.stepOneGeneration();
			sleepBetweenGenerations();
		}
	}

	private void sleepBetweenGenerations() {
		try {
			Thread.sleep(DELAY_BETWEEN_FRAMES_MS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}

}
