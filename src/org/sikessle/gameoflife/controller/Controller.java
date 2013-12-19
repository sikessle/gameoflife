package org.sikessle.gameoflife.controller;

import org.sikessle.gameoflife.model.Grid;

// TODO TESTING
public abstract class Controller {

	protected Grid grid;

	public Controller(Grid grid) {
		if (grid == null) {
			throw new NullPointerException();
		}
		this.grid = grid;
	}

	public void setGridSize(int rows, int columns) {
		if (rows > 0 && columns > 0) {
			grid.setGridSize(rows, columns);
		}
	}

	public void stepOneGeneration() {
		grid.stepNGenerations(1);
	}

	public void setCellToLivingAtPosition(int row, int column) {
		if (row >= 0 && column >= 0) {
			grid.changeCell(row, column, true);
		}
	}

	public void killAllCells() {
		grid.killAllCells();
	}

	abstract public void quit();

}
