package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.controller.GridController;

public class GliderCommand extends Command {

	private final GridController controller;
	private static final String DESCRIPTION = "g: generate glider";

	public GliderCommand(TextView ui) {
		if (ui == null) {
			throw new NullPointerException();
		}
		this.controller = ui.getGridController();
	}

	@Override
	public void handleIfResponsible(String command, Args arguments) {
		if (command.equals("g")) {
			showGlider();
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
