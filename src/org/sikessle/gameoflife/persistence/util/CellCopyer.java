package org.sikessle.gameoflife.persistence.util;

public class CellCopyer {

	public static boolean[][] copyCells(boolean[][] source) {
		boolean[][] copy = new boolean[source.length][];
		for (int i = 0; i < source.length; i++) {
			copy[i] = source[i].clone();
		}
		return copy;
	}

}
