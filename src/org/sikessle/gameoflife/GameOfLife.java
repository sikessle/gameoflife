package org.sikessle.gameoflife;

import org.sikessle.gameoflife.controller.GridController;
import org.sikessle.gameoflife.persistence.couchdb.CouchDBModule;
import org.sikessle.gameoflife.view.tui.TextView;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GameOfLife {

	public static void main(String[] args) {

		Injector injector = Guice.createInjector(new BaseModule(),
				new CouchDBModule());
		GridController controller = injector.getInstance(GridController.class);
		// new SwingView(controller);
		TextView textUI = new TextView(controller);
		textUI.readAndInterpretInLoopFromInputStream();
	}
}
