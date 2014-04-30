package org.sikessle.gameoflife.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.sikessle.gameoflife.model.Coordinate;
import org.sikessle.gameoflife.model.Figure;
import org.sikessle.gameoflife.model.Grid;

import com.google.inject.Inject;

public class FigureSpawner {

	@Inject
	private Coordinate target;

	public void spawn(Figure figure, Grid grid) {
		List<Coordinate> coords = getTranslatedCoordinates(figure
				.getCoordinates());

		for (Coordinate coord : coords) {
			grid.changeCell(coord.getRow(), coord.getColumn(), true);
		}
	}

	public void setTarget(Coordinate target) {
		if (target == null) {
			return;
		}
		this.target = target;
	}

	private List<Coordinate> getTranslatedCoordinates(List<Coordinate> source) {
		List<Coordinate> translated = new ArrayList<Coordinate>();

		for (Coordinate coord : source) {
			int x = coord.getRow() + target.getRow();
			int y = coord.getColumn() + target.getColumn();
			translated.add(new CoordinateImpl(x, y));
		}

		return translated;
	}

}
