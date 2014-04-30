package org.sikessle.gameoflife.figures.impl;

import java.util.LinkedList;
import java.util.List;

import org.sikessle.gameoflife.figures.Coordinate;

public final class CoordinateTranslator {

	private CoordinateTranslator() {
		// utility class
	}

	public static List<Coordinate> translatePositiveBy(List<Coordinate> source,
			int xOffset, int yOffset) {
		if (source == null) {
			return null;
		}
		if (xOffset < 0 || yOffset < 0) {
			throw new IllegalArgumentException();
		}
		return getTranslatedCoordinates(source, xOffset, yOffset);
	}

	private static List<Coordinate> getTranslatedCoordinates(
			List<Coordinate> source, int xOffset, int yOffset) {
		List<Coordinate> translated = new LinkedList<Coordinate>();

		for (Coordinate coord : source) {
			int x = coord.getX() + xOffset;
			int y = coord.getY() + yOffset;
			translated.add(new CoordinateImpl(x, y));
		}

		return translated;
	}

}
