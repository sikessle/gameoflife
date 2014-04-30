package org.sikessle.gameoflife.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.sikessle.gameoflife.model.impl.CopyWorldGenerationStrategy;

public class CopyWorldGenerationStrategyPluginTest {

	private GenerationStrategyPlugin strategy;

	@Before
	public void setUp() throws Exception {
		strategy = new CopyWorldGenerationStrategy();
	}

	@Test
	public void testgetName() {
		assertEquals("Copy-World", strategy.getName());
	}

	@Test
	public void testNextStateOfLivingCell() {
		// must stay alive
		assertTrue(strategy.nextStateOfLivingCell(1));
		assertTrue(strategy.nextStateOfLivingCell(3));
		assertTrue(strategy.nextStateOfLivingCell(5));
		assertTrue(strategy.nextStateOfLivingCell(7));
		assertTrue(strategy.nextStateOfLivingCell(9));

		// must die
		assertFalse(strategy.nextStateOfLivingCell(0));
		assertFalse(strategy.nextStateOfLivingCell(2));
		assertFalse(strategy.nextStateOfLivingCell(4));
		assertFalse(strategy.nextStateOfLivingCell(6));
		assertFalse(strategy.nextStateOfLivingCell(8));
	}

	@Test
	public void testNextStateOfDeadCell() {
		// must be reborn
		assertTrue(strategy.nextStateOfDeadCell(1));
		assertTrue(strategy.nextStateOfDeadCell(3));
		assertTrue(strategy.nextStateOfDeadCell(5));
		assertTrue(strategy.nextStateOfDeadCell(7));
		assertTrue(strategy.nextStateOfDeadCell(9));

		// must stay dead
		assertFalse(strategy.nextStateOfDeadCell(0));
		assertFalse(strategy.nextStateOfDeadCell(2));
		assertFalse(strategy.nextStateOfDeadCell(4));
		assertFalse(strategy.nextStateOfDeadCell(6));
		assertFalse(strategy.nextStateOfDeadCell(8));
	}

}
