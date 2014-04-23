package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.controller.Controller;

public class GliderCommand extends Command {

	private final Controller controller;
	private static final String DESCRIPTION = "d: generate glider";

	public GliderCommand(Controller controller) {
		if (controller == null) {
			throw new NullPointerException();
		}
		this.controller = controller;
	}

	@Override
	public void handleOrPassOnCommand(String command, Args arguments) {
		if (command.equals("d")) {
			showGlider();
		} else {
			passOnToSuccessor(command, arguments);
		}
	}

	private void showGlider() {
		if (isGridNotLargeEnough()) {
			return;
		}
		controller.setCellToLivingAtPosition(0, 1);
		controller.setCellToLivingAtPosition(1, 2);
		controller.setCellToLivingAtPosition(2, 0);
		controller.setCellToLivingAtPosition(2, 1);
		controller.setCellToLivingAtPosition(2, 2);
	}

	private boolean isGridNotLargeEnough() {
		int rows = controller.getNumberOfRows();
		int columns = controller.getNumberOfColumns();

		return rows < 2 || columns < 2;
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}
}
