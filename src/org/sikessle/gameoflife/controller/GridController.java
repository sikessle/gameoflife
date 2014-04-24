package org.sikessle.gameoflife.controller;

import java.util.List;
import java.util.Observer;

import org.sikessle.gameoflife.util.Observable;

public interface GridController extends Observer, Observable {

	void saveGame(String gameName);

	void loadGame(String gameName);

	List<String> listGames();

	void setGridSize(int rows, int columns);

	void stepOneGeneration();

	void setCellToLivingAtPosition(int row, int column);

	void setCellToDeadAtPosition(int row, int column);

	boolean isCellAlive(int row, int column);

	void killAllCells();

	int getNumberOfRows();

	int getNumberOfColumns();

	boolean[][] getCells();

}