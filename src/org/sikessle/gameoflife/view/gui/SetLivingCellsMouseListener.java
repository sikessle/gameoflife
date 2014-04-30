package org.sikessle.gameoflife.view.gui;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import org.sikessle.gameoflife.controller.GridController;

public class SetLivingCellsMouseListener extends MouseInputAdapter {

	private final GridController controller;
	private final int cellSize;

	public SetLivingCellsMouseListener(GridController controller, int cellSize) {
		if (controller == null) {
			throw new IllegalArgumentException();
		}

		this.controller = controller;
		this.cellSize = cellSize;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseClicked(e);
		determineAndSetCellToLiving(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		determineAndSetCellToLiving(e);
	}

	private void determineAndSetCellToLiving(MouseEvent e) {
		int row = e.getY() / cellSize;
		int column = e.getX() / cellSize;

		controller.setCellToLivingAtPosition(row, column);
	}

}
