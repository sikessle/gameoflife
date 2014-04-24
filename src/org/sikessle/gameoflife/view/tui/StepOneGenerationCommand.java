package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.controller.GridController;

public class StepOneGenerationCommand extends Command {

	private final GridController controller;
	private String command;
	private Args arguments;
	private static final String DESCRIPTION = "n: step 1 generation";

	public StepOneGenerationCommand(TextView ui) {
		if (ui == null) {
			throw new NullPointerException();
		}
		this.controller = ui.getGridController();
	}

	@Override
	public void handleIfResponsible(String command, Args arguments) {
		this.command = command;
		this.arguments = arguments;

		if (isCorrectCommand()) {
			controller.stepOneGeneration();
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
