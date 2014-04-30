package org.sikessle.gameoflife.figures.impl;

public class Coordinate {

	private final int x;
	private final int y;

	public Coordinate(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IndexOutOfBoundsException();
		}
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
