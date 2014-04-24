package org.sikessle.gameoflife.persistence.util;

public class GridDto {

	private String gameName;

	private boolean[][] cells;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public boolean[][] getCells() {
		return copyCells(cells);
	}

	public void setCells(boolean[][] cells) {
		this.cells = copyCells(cells);
	}

	private boolean[][] copyCells(boolean[][] source) {
		boolean[][] copy = new boolean[source.length][];
		for (int i = 0; i < source.length; i++) {
			copy[i] = source[i].clone();
		}
		return copy;
	}
}
