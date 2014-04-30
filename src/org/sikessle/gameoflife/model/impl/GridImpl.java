package org.sikessle.gameoflife.model.impl;

import java.util.Arrays;
import java.util.Observer;

import org.sikessle.gameoflife.model.GenerationStrategyPlugin;
import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.util.ObservableImpl;

import com.google.inject.Inject;

public class GridImpl implements Grid {

	private static final int INIT_ROWS = 10;
	private static final int INIT_COLUMNS = 20;
	private boolean[][] cells;
	private int rows;
	private int columns;
	private final GenerationStepper generationStepper;
	private final ObservableImpl observable;

	@Inject
	public GridImpl(GenerationStrategyPlugin generationStrategy) {
		generationStepper = new GenerationStepper(generationStrategy);
		observable = new ObservableImpl();
		rows = INIT_ROWS;
		columns = INIT_COLUMNS;
		createGrid();
	}

	private void createGrid() {
		cells = new boolean[rows][columns];
	}

	@Override
	public void setGridSize(int rows, int columns) {
		if (rows < 0 || columns < 0) {
			return;
		}
		this.rows = rows;
		this.columns = columns;
		createGrid();
		setChangedAndNotify();
	}

	@Override
	public int getNumberOfRows() {
		return rows;
	}

	@Override
	public int getNumberOfColumns() {
		return columns;
	}

	@Override
	public boolean[][] getCells() {
		return cells;
	}

	@Override
	public void setCells(boolean[][] cells) {
		if (cells == null) {
			throw new NullPointerException();
		}
		if (cells.length == 0) {
			throw new IndexOutOfBoundsException();
		}
		this.rows = cells.length;
		this.columns = cells[0].length;
		this.cells = cells;
		setChangedAndNotify();
	}

	private void setChangedAndNotify() {
		observable.setChanged();
		observable.notifyObservers();
	}

	@Override
	public void stepNGenerations(int numberOfGenerations) {
		for (int i = 0; i < numberOfGenerations; i++) {
			stepOneGeneration();
		}
		setChangedAndNotify();
	}

	private void stepOneGeneration() {
		cells = generationStepper.getNextGeneration(cells);
	}

	@Override
	public void changeCell(int row, int column, boolean alive) {
		if (isOutOfBounds(row, column)) {
			return;
		}
		cells[row][column] = alive;
		setChangedAndNotify();
	}

	private boolean isOutOfBounds(int row, int column) {
		return row < 0 || row >= cells.length || column < 0
				|| column >= cells[row].length;
	}

	@Override
	public void killAllCells() {
		fillGridWithDeadCells();
		setChangedAndNotify();
	}

	private void fillGridWithDeadCells() {
		for (int i = 0; i < rows; i++) {
			Arrays.fill(cells[i], false);
		}
	}

	@Override
	public void addObserver(Observer observer) {
		observable.addObserver(observer);
	}

	@Override
	public void deleteObserver(Observer observer) {
		observable.deleteObserver(observer);
	}

	@Override
	public String getGenerationStrategyName() {
		return generationStepper.getGenerationStrategy().getName();
	}

	@Override
	public void setGenerationStrategy(
			GenerationStrategyPlugin generationStrategy) {
		this.generationStepper.setGenerationStrategy(generationStrategy);
	}

}
