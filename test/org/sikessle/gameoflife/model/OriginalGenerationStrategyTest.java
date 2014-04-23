package org.sikessle.gameoflife.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.sikessle.gameoflife.plugin.GenerationStrategyPlugin;
import org.sikessle.gameoflife.plugin.impl.OriginalGenerationStrategy;

public class OriginalGenerationStrategyTest {

	private GenerationStrategyPlugin strategy;

	@Before
	public void setUp() throws Exception {
		strategy = new OriginalGenerationStrategy();
	}

	@Test
	public void testNextStateOfLivingCell() {
		// must live on
		assertTrue(strategy.nextStateOfLivingCell(2));
		assertTrue(strategy.nextStateOfLivingCell(3));

		// must die
		assertFalse(strategy.nextStateOfLivingCell(1));
		assertFalse(strategy.nextStateOfLivingCell(4));
	}

	@Test
	public void testNextStateOfDeadCell() {
		// must be reborn
		assertTrue(strategy.nextStateOfDeadCell(3));

		// must stay dead
		assertFalse(strategy.nextStateOfDeadCell(2));
		assertFalse(strategy.nextStateOfDeadCell(4));
	}

}
