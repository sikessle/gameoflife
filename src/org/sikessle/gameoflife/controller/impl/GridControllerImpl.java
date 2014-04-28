package org.sikessle.gameoflife.controller.impl;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.sikessle.gameoflife.controller.GridController;
import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.persistence.GridDao;
import org.sikessle.gameoflife.util.ObservableImpl;

import com.google.inject.Inject;

// TODO TESTING
public class GridControllerImpl implements GridController {

	protected final ObservableImpl observable;
	protected Grid grid;
	@Inject
	protected GridDao gridDao;

	@Inject
	public GridControllerImpl(Grid grid) {
		if (grid == null) {
			throw new NullPointerException();
		}
		this.grid = grid;
		grid.addObserver(this);
		observable = new ObservableImpl();
	}

	@Override
	public boolean saveGame(String gameName) {
		return gridDao.saveOrUpdate(grid, gameName);
	}

	@Override
	public boolean loadGame(String gameName) {
		Grid loadedGrid = gridDao.getByName(gameName);
		if (loadedGrid != null) {
			loadedGrid.addObserver(this);
			grid = loadedGrid;
			observable.setChanged();
			observable.notifyObservers();
			return true;
		}
		return false;
	}

	@Override
	public List<String> listGames() {
		return gridDao.getAllGameNames();
	}

	@Override
	public void setGridSize(int rows, int columns) {
		grid.setGridSize(rows, columns);
	}

	@Override
	public void stepOneGeneration() {
		grid.stepNGenerations(1);
	}

	@Override
	public void setCellToLivingAtPosition(int row, int column) {
		grid.changeCell(row, column, true);
	}

	@Override
	public void setCellToDeadAtPosition(int row, int column) {
		grid.changeCell(row, column, false);
	}

	@Override
	public boolean isCellAlive(int row, int column) {
		return grid.getCells()[row][column];
	}

	@Override
	public void killAllCells() {
		grid.killAllCells();
	}

	@Override
	public int getNumberOfRows() {
		return grid.getNumberOfRows();
	}

	@Override
	public int getNumberOfColumns() {
		return grid.getNumberOfColumns();
	}

	@Override
	public boolean[][] getCells() {
		return grid.getCells();
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
	public void update(Observable o, Object arg) {
		observable.setChanged();
		observable.notifyObservers();
	}

	@Override
	public String getGenerationStrategyName() {
		return grid.getGenerationStrategyName();
	}

}
