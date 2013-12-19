package org.sikessle.gameoflife.view.tui;

import java.util.Iterator;

import org.sikessle.gameoflife.controller.Controller;

public class AnimateCommand extends Command {

	private final Controller controller;
	private String command;
	private Args arguments;
	private int frames;
	private static final int DELAY_BETWEEN_FRAMES_MS = 100;

	public AnimateCommand(Controller controller) {
		if (controller == null) {
			throw new NullPointerException();
		}
		this.controller = controller;
	}

	@Override
	public void handleOrPassOnCommand(String command, Args arguments) {
		this.command = command;
		this.arguments = arguments;

		parseArguments();

		if (isCorrectCommand()) {
			startAnimation();
		} else {
			passOnToSuccessor(command, arguments);
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
		if (!command.equals("a") || argsNotValid()) {
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

}
