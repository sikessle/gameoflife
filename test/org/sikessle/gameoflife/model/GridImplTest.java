package org.sikessle.gameoflife.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.sikessle.gameoflife.model.impl.GridImpl;

public class GridImplTest {

	private Grid grid;
	private ObserverDummy observer;

	@Before
	public void setUp() throws Exception {
		grid = new GridImpl(new AlwaysDeadGenerationStrategyPlugin());
		observer = new ObserverDummy();
	}

	@Test(expected = NullPointerException.class)
	public void testGridImpl() {
		new GridImpl(null);
	}

	@Test(expected = NullPointerException.class)
	public void testSetGenerationStrategyException() {
		grid.setGenerationStrategy(null);
	}

	@Test
	public void testSetGridSizeWithInvalidArgs() {
		int expectedRows = grid.getNumberOfRows();
		int expectedColumns = grid.getNumberOfColumns();

		grid.setGridSize(-1, -1);
		assertGridSizeAsGiven(expectedRows, expectedColumns);
		grid.setGridSize(1, -1);
		assertGridSizeAsGiven(expectedRows, expectedColumns);
		grid.setGridSize(-1, 1);
		assertGridSizeAsGiven(expectedRows, expectedColumns);
	}

	@Test
	public void testSetGridSizeWithValidArgs() {
		int expectedRows = 2;
		int expectedColumns = 3;

		grid.setGridSize(expectedRows, expectedColumns);
		assertGridSizeAsGiven(expectedRows, expectedColumns);
		assertAllCellsAreDead();
	}

	private void assertGridSizeAsGiven(int expectedRows, int expectedColumns) {
		assertEquals(expectedRows, grid.getNumberOfRows());
		assertEquals(expectedColumns, grid.getNumberOfColumns());
	}

	private void assertAllCellsAreDead() {
		boolean[][] cells = grid.getCells();

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				assertFalse(cells[i][j]);
			}
		}
	}

	@Test
	public void testGetNumberOfRows() {
		grid.setGridSize(3, 1);
		assertEquals(3, grid.getNumberOfRows());
	}

	@Test
	public void testGetNumberOfColumns() {
		grid.setGridSize(1, 3);
		assertEquals(3, grid.getNumberOfColumns());
	}

	@Test
	public void testStepNGenerations() {
		grid.setGridSize(5, 5);
		grid.changeCell(0, 0, true);
		grid.stepNGenerations(1);
		assertAllCellsAreDead();
		grid.changeCell(0, 0, true);
		grid.stepNGenerations(3);
		assertAllCellsAreDead();
	}

	@Test
	public void testChangeCellWithInvalidArgs() {
		grid.setGridSize(10, 10);
		assertAllCellsAreDead();
		grid.changeCell(-1, 3, true);
		assertAllCellsAreDead();
		grid.changeCell(-1, -3, true);
		assertAllCellsAreDead();
		grid.changeCell(1, -3, true);
		assertAllCellsAreDead();
		grid.changeCell(10, 1, true);
		assertAllCellsAreDead();
		grid.changeCell(1, 10, true);
		assertAllCellsAreDead();
		grid.changeCell(10, 10, true);
		assertAllCellsAreDead();
	}

	@Test
	public void testChangeCellWithValidArgs() {
		grid.setGridSize(10, 10);
		assertAllCellsAreDead();
		grid.changeCell(2, 3, true);

		boolean[][] cells = grid.getCells();
		assertTrue(cells[2][3]);
	}

	@Test
	public void testSetGenerationStrategy() {
		grid.setGenerationStrategy(new AlwaysLivingGenerationStrategyPlugin());
		grid.stepNGenerations(1);
		boolean cells[][] = grid.getCells();
		assertTrue(cells[0][0]);
	}

	@Test
	public void testKillAllCells() {
		grid.setGridSize(3, 3);
		grid.changeCell(1, 1, true);
		grid.killAllCells();
		assertAllCellsAreDead();
	}

	@Test
	public void testAddObserver() {
		grid.addObserver(observer);
		grid.stepNGenerations(1);
		assertObserverHasBeenNotified();
	}

	@Test
	public void deleteAddObserver() {
		grid.addObserver(observer);
		grid.deleteObserver(observer);
		grid.stepNGenerations(1);
		assertObserverHasNotBeenNotified();
	}

	private void assertObserverHasBeenNotified() {
		assertTrue(observer.hasBeenNotified());
	}

	private void assertObserverHasNotBeenNotified() {
		assertFalse(observer.hasBeenNotified());
	}
}
