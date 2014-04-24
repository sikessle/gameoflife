package org.sikessle.gameoflife.persistence.db4o;

import org.sikessle.gameoflife.persistence.util.GridDto;

public class GridDb4oDto implements GridDto {

	private String gameName;
	private boolean[][] cells;

	@Override
	public String getGameName() {
		return gameName;
	}

	@Override
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@Override
	public boolean[][] getCells() {
		return cells;
	}

	@Override
	public void setCells(boolean[][] cells) {
		this.cells = cells;
	}

}
