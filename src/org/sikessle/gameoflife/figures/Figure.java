package org.sikessle.gameoflife.figures;

import java.util.List;

import org.sikessle.gameoflife.figures.impl.Coordinate;

public interface Figure {

	String getName();

	List<Coordinate> getCoordinates();

}
