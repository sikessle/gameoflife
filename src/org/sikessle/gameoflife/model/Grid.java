package org.sikessle.gameoflife.model;

import org.sikessle.gameoflife.util.Observable;

public interface Grid extends Observable {

	boolean[][] getCells();

	int getNumberOfRows();

	int getNumberOfColumns();

	void setGridSize(int rows, int columns);

	void stepNGenerations(int numberOfGenerations);

	void setGenerationStrategy(GenerationStrategy generationStrategy);

	void changeCell(int row, int column, boolean alive);

	void killAllCells();

}
