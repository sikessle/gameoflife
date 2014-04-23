package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.controller.Controller;

public class StepOneGenerationCommand extends Command {

	private final Controller controller;
	private String command;
	private Args arguments;
	private static final String DESCRIPTION = "n: step 1 generation";

	public StepOneGenerationCommand(Controller controller) {
		if (controller == null) {
			throw new NullPointerException();
		}
		this.controller = controller;
	}

	@Override
	public void handleOrPassOnCommand(String command, Args arguments) {
		this.command = command;
		this.arguments = arguments;

		if (isCorrectCommand()) {
			controller.stepOneGeneration();
		} else {
			passOnToSuccessor(command, arguments);
		}
	}

	private boolean isCorrectCommand() {
		if (!command.equals("n") || arguments.size() > 0) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}
}
