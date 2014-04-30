package org.sikessle.gameoflife.persistence.util;

public final class CellCopyer {

	private CellCopyer() {
		// static utility class
	}

	public static boolean[][] copyCells(boolean[][] source) {
		if (source == null) {
			return null;
		}
		boolean[][] copy = new boolean[source.length][];
		for (int i = 0; i < source.length; i++) {
			copy[i] = source[i].clone();
		}
		return copy;
	}

}
