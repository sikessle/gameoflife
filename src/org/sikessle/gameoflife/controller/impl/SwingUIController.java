package org.sikessle.gameoflife.controller.impl;

import javax.swing.SwingUtilities;

import org.sikessle.gameoflife.controller.Controller;
import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.view.gui.SwingUI;

public final class SwingUIController extends Controller {

	SwingUI ui;

	public SwingUIController(Grid grid) {
		super(grid);

		createGUI();
	}

	private void createGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ui = new SwingUI(grid, SwingUIController.this);
				grid.setGridSize(20, 40);
			}
		});
	}

	@Override
	public void quit() {

	}

}
