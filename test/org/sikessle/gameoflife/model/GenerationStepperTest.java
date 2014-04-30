package org.sikessle.gameoflife.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sikessle.gameoflife.model.impl.GenerationStepper;

public class GenerationStepperTest {

	private GenerationStepper stepper;

	@Test(expected = NullPointerException.class)
	public void testGetNextGenerationWithNullArgument() {
		setGenerationStepper(new AlwaysLivingGenerationStrategyPlugin());
		stepper.getNextGeneration(null);
	}

	private void setGenerationStepper(GenerationStrategyPlugin strategy) {
		stepper = new GenerationStepper(strategy);
	}

	@Test
	public void testGetNextGenerationWithLivingStrategy() {
		setGenerationStepper(new AlwaysLivingGenerationStrategyPlugin());
		boolean[][] cells = create3on3DeadGrid();
		cells[0][0] = false;

		boolean[][] nextGen = stepper.getNextGeneration(cells);
		assertEveryCellMatchesGivenValue(nextGen, true);
	}

	@Test
	public void testGetNextGenerationWithDeadStrategy() {
		setGenerationStepper(new AlwaysDeadGenerationStrategyPlugin());
		boolean[][] cells = create3on3DeadGrid();
		cells[0][0] = true;

		boolean[][] nextGen = stepper.getNextGeneration(cells);
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

	@Test
	public void testGetGenerationStrategy() {
		GenerationStrategyPlugin expected = new AlwaysDeadGenerationStrategyPlugin();
		setGenerationStepper(expected);
		GenerationStrategyPlugin actual = stepper.getGenerationStrategy();

		assertSame(expected, actual);
	}

}
