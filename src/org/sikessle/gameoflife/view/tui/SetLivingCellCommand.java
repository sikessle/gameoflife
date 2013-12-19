package org.sikessle.gameoflife.view.tui;

import java.util.Iterator;

import org.sikessle.gameoflife.controller.Controller;

public class SetLivingCellCommand extends Command {

	private final Controller controller;
	private String command;
	private Args arguments;
	private int row;
	private int column;

	public SetLivingCellCommand(Controller controller) {
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
			controller.setCellToLivingAtPosition(row, column);
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
			row = Integer.parseInt(iterator.next());
			column = Integer.parseInt(iterator.next());
		} catch (NumberFormatException e) {
			row = 0;
			column = 0;
		}
	}

	private boolean isCorrectCommand() {
		if (!command.equals("l") || argsNotValid()) {
			return false;
		}

		return true;
	}

	private boolean argsNotValid() {
		return row < 0 || column < 0;
	}

}
