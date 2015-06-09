package org.sikessle.gameoflife.util;

import java.util.Observer;

public interface Observable {
	void addObserver(Observer observer);

	void deleteObserver(Observer observer);
}
