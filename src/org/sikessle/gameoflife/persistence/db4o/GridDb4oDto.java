package org.sikessle.gameoflife.persistence.db4o;

import org.sikessle.gameoflife.persistence.GridDto;
import org.sikessle.gameoflife.util.ArrayCopier;

public class GridDb4oDto implements GridDto {

	private String gameName;
	private boolean[][] cells;
	private int steppedGenerations;

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
		return ArrayCopier.copy(cells);
	}

	@Override
	public void setCells(boolean[][] cells) {
		this.cells = ArrayCopier.copy(cells);
	}

	@Override
	public int getNumberOfSteppedGenerations() {
		return steppedGenerations;
	}

	@Override
	public void setNumberOfSteppedGenerations(int generations) {
		steppedGenerations = generations;
	}

}
