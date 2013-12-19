package org.sikessle.gameoflife.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GenerationStrategyTest {

	private GenerationStrategy strategy;

	@Test(expected = NullPointerException.class)
	public void testGetNextGenerationWithNullArgument() {
		strategy = new AlwaysLivingGenerationStrategy();
		strategy.getNextGeneration(null);
	}

	@Test
	public void testGetNextGenerationWithLivingStrategy() {
		strategy = new AlwaysLivingGenerationStrategy();
		boolean[][] cells = create3on3DeadGrid();
		cells[0][0] = false;

		boolean[][] nextGen = strategy.getNextGeneration(cells);
		assertEveryCellMatchesGivenValue(nextGen, true);
	}

	@Test
	public void testGetNextGenerationWithDeadStrategy() {
		strategy = new AlwaysDeadGenerationStrategy();
		boolean[][] cells = create3on3DeadGrid();
		cells[0][0] = true;

		boolean[][] nextGen = strategy.getNextGeneration(cells);
		assertEveryCellMatchesGivenValue(nextGen, false);
	}

	private boolean[][] create3on3DeadGrid() {
		boolean[][] deadGrid = new boolean[3][3];
		return deadGrid;
	}

	private void assertEveryCellMatchesGivenValue(boolean[][] cells,
			boolean value) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				assertTrue(cells[i][j] == value);
			}
		}
	}

}
