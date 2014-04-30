package org.sikessle.gameoflife.model.impl;

import org.sikessle.gameoflife.model.GenerationStrategyPlugin;

public class GenerationStepper {

	private boolean[][] currentGeneration;
	private boolean[][] nextGeneration;
	private int currentRow;
	private int currentColumn;
	private GenerationStrategyPlugin generationStrategy;

	public GenerationStepper(GenerationStrategyPlugin generationStrategy) {
		if (generationStrategy == null) {
			throw new IllegalArgumentException();
		}
		this.generationStrategy = generationStrategy;
	}

	public boolean[][] getNextGeneration(boolean[][] cells) {
		if (cells == null) {
			throw new IllegalArgumentException();
		}

		currentGeneration = cells;
		nextGeneration = new boolean[currentGeneration.length][];

		for (int i = 0; i < currentGeneration.length; i++) {
			nextGeneration[i] = new boolean[currentGeneration[i].length];

			for (int j = 0; j < currentGeneration[i].length; j++) {
				currentRow = i;
				currentColumn = j;
				saveNextGenerationOfCurrentCell();
			}
		}

		return nextGeneration;
	}

	public GenerationStrategyPlugin getGenerationStrategy() {
		return generationStrategy;
	}

	public void setGenerationStrategy(
			GenerationStrategyPlugin generationStrategy) {
		if (generationStrategy == null) {
			throw new IllegalArgumentException();
		}
		this.generationStrategy = generationStrategy;
	}

	private void saveNextGenerationOfCurrentCell() {
		int livingNeighbors = countLivingNeighbors();

		if (isCellAlive(currentRow, currentColumn)) {
			nextGeneration[currentRow][currentColumn] = generationStrategy
					.nextStateOfLivingCell(livingNeighbors);
		} else {
			nextGeneration[currentRow][currentColumn] = generationStrategy
					.nextStateOfDeadCell(livingNeighbors);
		}
	}

	private int countLivingNeighbors() {
		int livingNeighbors = 0;

		for (int i = currentRow - 1; i <= currentRow + 1; i++) {
			if (isRowIndexOutOfBounds(i)) {
				continue;
			}
			livingNeighbors += countLivingNeighborsInRow(i);
		}
		return livingNeighbors;
	}

	private int countLivingNeighborsInRow(int i) {
		int livingNeighborsInRow = 0;

		for (int j = currentColumn - 1; j <= currentColumn + 1; j++) {
			if (isColumnIndexOutOfBounds(i, j)) {
				continue;
			}

			if (isNotCurrentCell(i, j) && isCellAlive(i, j)) {
				livingNeighborsInRow++;
			}
		}
		return livingNeighborsInRow;
	}

	private boolean isColumnIndexOutOfBounds(int row, int column) {
		return column < 0 || column >= currentGeneration[row].length;
	}

	private boolean isRowIndexOutOfBounds(int row) {
		return row < 0 || row >= currentGeneration.length;
	}

	private boolean isNotCurrentCell(int row, int column) {
		return (row != currentRow) || (column != currentColumn);
	}

	private boolean isCellAlive(int row, int column) {
		return currentGeneration[row][column];
	}

}
