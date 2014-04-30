package org.sikessle.gameoflife.figures.impl;

import org.sikessle.gameoflife.figures.Coordinate;

public class CoordinateImpl implements Coordinate {

	private final int x;
	private final int y;

	public CoordinateImpl(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IndexOutOfBoundsException();
		}
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

}
