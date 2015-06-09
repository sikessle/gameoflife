package org.sikessle.gameoflife.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.sikessle.gameoflife.model.impl.OriginalGenerationStrategy;

public class OriginalGenerationStrategyPluginTest {

	private GenerationStrategyPlugin strategy;

	@Before
	public void setUp() throws Exception {
		strategy = new OriginalGenerationStrategy();
	}

	@Test
	public void testgetName() {
		assertEquals("23/3-World", strategy.getName());
	}

	@Test
	public void testNextStateOfLivingCell() {
		// must stay alive
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
