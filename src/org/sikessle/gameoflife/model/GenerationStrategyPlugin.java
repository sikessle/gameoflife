package org.sikessle.gameoflife.model;

public abstract class GenerationStrategyPlugin {

	private boolean[][] currentGeneration;
	private boolean[][] nextGeneration;
	private int currentRow;
	private int currentColumn;

	public boolean[][] getNextGeneration(boolean[][] cells) {
		if (cells == null) {
			throw new NullPointerException();
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

	private void saveNextGenerationOfCurrentCell() {
		int livingNeighbors = countLivingNeighbors();

		if (isCellAlive(currentRow, currentColumn)) {
			nextGeneration[currentRow][currentColumn] = nextStateOfLivingCell(livingNeighbors);
		} else {
			nextGeneration[currentRow][currentColumn] = nextStateOfDeadCell(livingNeighbors);
		}
	}

	private int countLivingNeighbors() {
		int livingNeighbors = 0;

		for (int i = currentRow - 1; i <= currentRow + 1; i++) {
			if (i < 0 || i >= currentGeneration.length) {
				continue;
			}
			for (int j = currentColumn - 1; j <= currentColumn + 1; j++) {
				if (j < 0 || j >= currentGeneration[i].length) {
					continue;
				}

				if (isNotCurrentCell(i, j) && isCellAlive(i, j)) {
					livingNeighbors++;
				}
			}
		}
		return livingNeighbors;
	}

	private boolean isNotCurrentCell(int i, int j) {
		return (i != currentRow) || (j != currentColumn);
	}

	private boolean isCellAlive(int row, int column) {
		return currentGeneration[row][column];
	}

	protected abstract boolean nextStateOfLivingCell(int livingNeighbors);

	protected abstract boolean nextStateOfDeadCell(int livingNeighbors);

	public abstract String getName();

}
