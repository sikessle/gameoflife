package org.sikessle.gameoflife.model;

public interface Grid extends GridReadOnly {

	void setGridSize(int rows, int columns);

	void stepNGenerations(int numberOfGenerations);

	void setGenerationStrategy(GenerationStrategy generationStrategy);

	void changeCell(int row, int column, boolean alive);

	void killAllCells();

}
