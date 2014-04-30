package org.sikessle.gameoflife.figures.impl;

import java.util.LinkedList;
import java.util.List;

import org.sikessle.gameoflife.figures.Coordinate;
import org.sikessle.gameoflife.figures.Figure;

public class RPentomino implements Figure {

	@Override
	public String getName() {
		return "r-Pentomino";
	}

	@Override
	public List<Coordinate> getCoordinates() {
		List<Coordinate> coordinates = new LinkedList<Coordinate>();

		coordinates.add(new CoordinateImpl(0, 1));
		coordinates.add(new CoordinateImpl(0, 2));
		coordinates.add(new CoordinateImpl(1, 0));
		coordinates.add(new CoordinateImpl(1, 1));
		coordinates.add(new CoordinateImpl(2, 1));

		return coordinates;
	}

}
