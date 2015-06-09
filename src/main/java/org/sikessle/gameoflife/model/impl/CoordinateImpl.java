package org.sikessle.gameoflife.model.impl;

import org.sikessle.gameoflife.model.Coordinate;

public class CoordinateImpl implements Coordinate {

	private int row;
	private int column;

	public CoordinateImpl(int row, int column) {
		checkPositive(row);
		checkPositive(column);
		this.row = row;
		this.column = column;
	}

	private void checkPositive(int i) {
		if (i < 0) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	@Override
	public void setRow(int row) {
		checkPositive(row);
		this.row = row;
	}

	@Override
	public void setColumn(int column) {
		checkPositive(column);
		this.column = column;
	}

}
