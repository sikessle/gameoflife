package org.sikessle.gameoflife.persistence.util;

public interface GridDto {

	String getGameName();

	void setGameName(String gameName);

	boolean[][] getCells();

	void setCells(boolean[][] cells);

}