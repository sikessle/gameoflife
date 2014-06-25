package org.sikessle.gameoflife.persistence;

public interface GridDto {

	String getGameName();

	void setGameName(String gameName);

	boolean[][] getCells();

	void setCells(boolean[][] cells);

	int getNumberOfSteppedGenerations();

	void setNumberOfSteppedGenerations(int generations);

}