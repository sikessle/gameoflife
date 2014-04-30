package org.sikessle.gameoflife.figures.impl;

import java.util.LinkedList;
import java.util.List;

import org.sikessle.gameoflife.figures.Coordinate;
import org.sikessle.gameoflife.figures.Figure;

public class Glider implements Figure {

	@Override
	public String getName() {
		return "Glider";
	}

	@Override
	public List<Coordinate> getCoordinates() {
		List<Coordinate> coordinates = new LinkedList<Coordinate>();

		coordinates.add(new CoordinateImpl(0, 1));
		coordinates.add(new CoordinateImpl(1, 2));
		coordinates.add(new CoordinateImpl(2, 0));
		coordinates.add(new CoordinateImpl(2, 1));
		coordinates.add(new CoordinateImpl(2, 2));

		return coordinates;
	}

}
