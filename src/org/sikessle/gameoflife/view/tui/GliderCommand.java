package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.controller.GridController;

public class GliderCommand extends Command {

	private final GridController controller;
	private static final String KEY = "g";
	private static final String DESCRIPTION = KEY + ": generate glider";

	public GliderCommand(TextView ui) {
		if (ui == null) {
			throw new IllegalArgumentException();
		}
		this.controller = ui.getGridController();
	}

	@Override
	protected boolean isResponsible() {
		return getCommand().equals(KEY) && getArguments().size() == 0;
	}

	@Override
	public void execute() {
		showGlider();
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
