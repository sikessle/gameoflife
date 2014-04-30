package org.sikessle.gameoflife.figures.impl;

import java.util.LinkedList;
import java.util.List;

import org.sikessle.gameoflife.figures.Figure;

public class Glider implements Figure {

	@Override
	public String getName() {
		return "Glider";
	}

	@Override
	public List<Coordinate> getCoordinates() {
		List<Coordinate> coordinates = new LinkedList<Coordinate>();

		coordinates.add(new Coordinate(0, 1));
		coordinates.add(new Coordinate(1, 2));
		coordinates.add(new Coordinate(2, 0));
		coordinates.add(new Coordinate(2, 1));
		coordinates.add(new Coordinate(2, 2));

		return coordinates;
	}

}
