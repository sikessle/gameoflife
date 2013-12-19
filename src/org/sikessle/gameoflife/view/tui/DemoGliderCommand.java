package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.controller.Controller;

public class DemoGliderCommand extends Command {

	private final Controller controller;

	public DemoGliderCommand(Controller controller) {
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
		controller.setGridSize(40, 60);
		controller.setCellToLivingAtPosition(0, 1);
		controller.setCellToLivingAtPosition(1, 2);
		controller.setCellToLivingAtPosition(2, 0);
		controller.setCellToLivingAtPosition(2, 1);
		controller.setCellToLivingAtPosition(2, 2);
	}

}
