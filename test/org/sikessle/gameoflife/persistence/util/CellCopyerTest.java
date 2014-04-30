package org.sikessle.gameoflife.persistence.util;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.sikessle.gameoflife.util.CellCopyer;

public class CellCopyerTest {

	@Test
	public void testCopyNull() {
		boolean[][] copy = CellCopyer.copyCells(null);
		assertNull(copy);
	}

}
