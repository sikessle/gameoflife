package org.sikessle.gameoflife.model;

import java.util.Observable;
import java.util.Observer;

public class ObserverDummy implements Observer {

	private boolean beenNotified = false;

	@Override
	public void update(Observable o, Object arg) {
		beenNotified = true;
	}

	public boolean hasBeenNotified() {
		return beenNotified;
	}

	public void resetBeenNotified() {
		beenNotified = false;
	}

}
