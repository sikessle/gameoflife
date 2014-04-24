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
		return cells;
	}

	public void setCells(boolean[][] cells) {
		this.cells = cells;
	}

}
