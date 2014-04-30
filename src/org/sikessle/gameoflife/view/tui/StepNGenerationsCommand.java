package org.sikessle.gameoflife.view.tui;

import java.util.Iterator;

import org.sikessle.gameoflife.controller.GridController;

public class StepNGenerationsCommand extends Command {

	private final GridController controller;
	private int frames;
	private static final int DELAY_BETWEEN_FRAMES_MS = 100;
	private static final String KEY = "n";
	private static final String DESCRIPTION = KEY + " [x]: step x generations";

	public StepNGenerationsCommand(TextView ui) {
		if (ui == null) {
			throw new NullPointerException();
		}
		this.controller = ui.getGridController();
	}

	@Override
	protected boolean isResponsible() {
		parseArguments();
		return getCommand().equals(KEY) && argsAreValid();
	}

	@Override
	protected void execute() {
		startAnimation();
	}

	private void parseArguments() {
		if (getArguments().size() < 1) {
			return;
		}

		Iterator<String> iterator = getArguments().iterator();

		try {
			frames = Integer.parseInt(iterator.next());
		} catch (NumberFormatException e) {
			frames = 0;
		}
	}

	private boolean argsAreValid() {
		return frames > 0;
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
