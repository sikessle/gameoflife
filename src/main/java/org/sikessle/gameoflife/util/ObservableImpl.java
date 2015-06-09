package org.sikessle.gameoflife.util;

import java.util.Observable;

public class ObservableImpl extends Observable {
	@Override
	public synchronized void setChanged() {
		super.setChanged();
	}
}
