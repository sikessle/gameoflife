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
		grid.setGridSize(rows, columns);
	}

	public void stepOneGeneration() {
		grid.stepNGenerations(1);
	}

	public void setCellToLivingAtPosition(int row, int column) {
		grid.changeCell(row, column, true);
	}

	public void killAllCells() {
		grid.killAllCells();
	}

	public int getNumberOfRows() {
		return grid.getNumberOfRows();
	}

	public int getNumberOfColumns() {
		return grid.getNumberOfColumns();
	}

	abstract public void quit();

}
