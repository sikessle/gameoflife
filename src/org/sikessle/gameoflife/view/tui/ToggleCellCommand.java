package org.sikessle.gameoflife.view.tui;

import java.util.Iterator;

import org.sikessle.gameoflife.controller.Controller;

public class ToggleCellCommand extends Command {

	private final Controller controller;
	private String command;
	private Args arguments;
	private int row;
	private int column;
	private static final String DESCRIPTION = "t [x] [y]: toggle cell";

	public ToggleCellCommand(Controller controller) {
		if (controller == null) {
			throw new NullPointerException();
		}
		this.controller = controller;
	}

	@Override
	public void handleIfResponsible(String command, Args arguments) {
		this.command = command;
		this.arguments = arguments;

		parseArguments();

		if (isCorrectCommand()) {
			toggleCell();
		}
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

	private boolean isCorrectCommand() {
		if (!command.equals("t") || argsNotValid()) {
			return false;
		}

		return true;
	}

	private boolean argsNotValid() {
		return row < 0 || column < 0;
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}
}
