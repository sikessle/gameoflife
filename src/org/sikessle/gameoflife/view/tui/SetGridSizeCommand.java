package org.sikessle.gameoflife.view.tui;

import java.util.Iterator;

import org.sikessle.gameoflife.controller.Controller;

public class SetGridSizeCommand extends Command {

	private final Controller controller;
	private String command;
	private Args arguments;
	private int rows;
	private int columns;
	private static final String DESCRIPTION = "s [x] [y]: set grid size";

	public SetGridSizeCommand(Controller controller) {
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
			controller.setGridSize(rows, columns);
		} else {
			passOnToSuccessor(command, arguments);
		}
	}

	private void parseArguments() {
		if (arguments.size() < 2) {
			return;
		}

		Iterator<String> iterator = arguments.iterator();

		try {
			rows = Integer.parseInt(iterator.next());
			columns = Integer.parseInt(iterator.next());
		} catch (NumberFormatException e) {
			rows = 0;
			columns = 0;
		}
	}

	private boolean isCorrectCommand() {
		if (!command.equals("s") || argsNotValid()) {
			return false;
		}

		return true;
	}

	private boolean argsNotValid() {
		return rows <= 0 || columns <= 0;
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}

}
