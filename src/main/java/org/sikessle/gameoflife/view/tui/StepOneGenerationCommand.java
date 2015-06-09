package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.controller.GridController;

public class StepOneGenerationCommand extends Command {

	private final GridController controller;
	private static final String KEY = "n";
	private static final String DESCRIPTION = KEY + ": step 1 generation";

	public StepOneGenerationCommand(TextView textUi) {
		if (textUi == null) {
			throw new IllegalArgumentException();
		}
		this.controller = textUi.getGridController();
	}

	@Override
	protected boolean isResponsible() {
		return getCommand().equals(KEY) && getArguments().size() == 0;
	}

	@Override
	protected void execute() {
		controller.stepOneGeneration();
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}
}
