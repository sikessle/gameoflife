package org.sikessle.gameoflife.persistence.util;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.sikessle.gameoflife.util.ArrayCopier;

public class ArrayCopierTest {

	@Test
	public void testCopyNull() {
		boolean[][] copy = ArrayCopier.copy(null);
		assertNull(copy);
	}

}
