package org.sikessle.gameoflife.model;

import org.sikessle.gameoflife.util.Observable;

public interface GridReadOnly extends Observable {
	boolean[][] getCells();

	int getNumberOfRows();

	int getNumberOfColumns();
}
