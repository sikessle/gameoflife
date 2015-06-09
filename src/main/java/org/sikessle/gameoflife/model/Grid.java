package org.sikessle.gameoflife.model;

import org.sikessle.gameoflife.util.Observable;

public interface Grid extends Observable {

	boolean[][] getCells();

	void setCells(boolean[][] cells);

	int getNumberOfRows();

	int getNumberOfColumns();

	void setGridSize(int rows, int columns);

	void stepNGenerations(int numberOfGenerations);

	void changeCell(int row, int column, boolean alive);

	void killAllCells();

	String getGenerationStrategyName();

	void setGenerationStrategy(GenerationStrategyPlugin generationStrategy);

	void spawnFigure(Figure figure, int row, int column);

	int getNumberOfSteppedGenerations();

	void setNumberOfSteppedGenerations(int generations);

}
