package org.sikessle.gameoflife.view.tui;

import java.util.Iterator;

import org.sikessle.gameoflife.controller.GridController;

public class ToggleCellCommand extends Command {

	private final GridController controller;
	private int row = -1;
	private int column = -1;
	private static final String KEY = "t";
	private static final String DESCRIPTION = KEY + " [x] [y]: toggle cell";

	public ToggleCellCommand(TextView ui) {
		if (ui == null) {
			throw new NullPointerException();
		}
		this.controller = ui.getGridController();
	}

	@Override
	protected boolean isResponsible() {
		parseArguments();
		return command.equals(KEY) && argsAreValid();
	}

	@Override
	protected void execute() {
		toggleCell();
	}

	private void toggleCell() {
		if (controller.isCellAlive(row, column)) {
			controller.setCellToDeadAtPosition(row, column);
		} else {
			controller.setCellToLivingAtPosition(row, column);
		}
	}

	private void parseArguments() {
		if (arguments.size() < 2) {
			return;
		}

		Iterator<String> iterator = arguments.iterator();

		try {
			row = Integer.parseInt(iterator.next());
			column = Integer.parseInt(iterator.next());
		} catch (NumberFormatException e) {
			row = 0;
			column = 0;
		}
	}

	private boolean argsAreValid() {
		return row >= 0 && column >= 0;
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}
}
