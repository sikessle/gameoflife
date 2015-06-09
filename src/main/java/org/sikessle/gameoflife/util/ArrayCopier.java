package org.sikessle.gameoflife.util;

public final class ArrayCopier {

	private ArrayCopier() {
		// static utility class
	}

	public static boolean[][] copy(boolean[][] source) {
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
