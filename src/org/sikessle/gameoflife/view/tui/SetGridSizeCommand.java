package org.sikessle.gameoflife.view.tui;

import java.util.Iterator;

import org.sikessle.gameoflife.controller.GridController;

public class SetGridSizeCommand extends Command {

	private final GridController controller;
	private int rows;
	private int columns;
	private static final String KEY = "s";
	private static final String DESCRIPTION = KEY + " [x] [y]: set grid size";

	public SetGridSizeCommand(TextView ui) {
		if (ui == null) {
			throw new IllegalArgumentException();
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
		controller.setGridSize(rows, columns);
	}

	private void parseArguments() {
		if (getArguments().size() < 2) {
			return;
		}

		Iterator<String> iterator = getArguments().iterator();

		try {
			rows = Integer.parseInt(iterator.next());
			columns = Integer.parseInt(iterator.next());
		} catch (NumberFormatException e) {
			rows = 0;
			columns = 0;
		}
	}

	private boolean argsAreValid() {
		return rows > 0 && columns > 0;
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}

}
