package org.sikessle.gameoflife.model;

import java.util.Arrays;
import java.util.Observer;

import org.sikessle.gameoflife.util.ObservableImpl;

public class GridImpl implements Grid {

	private boolean[][] cells;
	private int rows;
	private int columns;
	private GenerationStrategy generationStrategy;
	private final ObservableImpl observable;

	public GridImpl(GenerationStrategy generationStrategy) {
		if (generationStrategy == null) {
			throw new NullPointerException();
		}
		this.generationStrategy = generationStrategy;
		observable = new ObservableImpl();
		rows = 1;
		columns = 1;
		createGrid();
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

	private void createGrid() {
		cells = new boolean[rows][columns];
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
		cells = generationStrategy.getNextGeneration(cells);
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
	public void setGenerationStrategy(GenerationStrategy generationStrategy) {
		this.generationStrategy = generationStrategy;
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

}
