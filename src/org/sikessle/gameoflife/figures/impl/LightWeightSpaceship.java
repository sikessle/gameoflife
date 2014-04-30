package org.sikessle.gameoflife.figures.impl;

import java.util.LinkedList;
import java.util.List;

import org.sikessle.gameoflife.figures.Coordinate;
import org.sikessle.gameoflife.figures.Figure;

public class LightWeightSpaceship implements Figure {

	@Override
	public String getName() {
		return "Light-Weight Spaceship";
	}

	@Override
	public List<Coordinate> getCoordinates() {
		List<Coordinate> coordinates = new LinkedList<Coordinate>();

		coordinates.add(new CoordinateImpl(0, 1));
		coordinates.add(new CoordinateImpl(1, 0));
		coordinates.add(new CoordinateImpl(2, 0));
		coordinates.add(new CoordinateImpl(3, 0));
		coordinates.add(new CoordinateImpl(3, 1));
		coordinates.add(new CoordinateImpl(3, 2));
		coordinates.add(new CoordinateImpl(3, 3));
		coordinates.add(new CoordinateImpl(2, 4));
		coordinates.add(new CoordinateImpl(0, 4));

		return coordinates;
	}

}
