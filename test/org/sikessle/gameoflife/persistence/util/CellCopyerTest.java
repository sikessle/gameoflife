package org.sikessle.gameoflife.persistence.util;

import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CellCopyerTest {

	@Test
	public void testCopyNull() {
		boolean[][] copy = CellCopyer.copyCells(null);
		assertNull(copy);
	}

}
