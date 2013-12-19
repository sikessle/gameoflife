package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.controller.Controller;

public class StepOneGenerationCommand extends Command {

	private final Controller controller;
	private String command;

	public StepOneGenerationCommand(Controller controller) {
		if (controller == null) {
			throw new NullPointerException();
		}
		this.controller = controller;
	}

	@Override
	public void handleOrPassOnCommand(String command, Args arguments) {
		this.command = command;

		if (isCorrectCommand()) {
			controller.stepOneGeneration();
		} else {
			passOnToSuccessor(command, arguments);
		}
	}

	private boolean isCorrectCommand() {
		if (!command.equals("n")) {
			return false;
		}

		return true;
	}

}
