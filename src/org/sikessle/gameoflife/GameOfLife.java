package org.sikessle.gameoflife;

import org.apache.log4j.PropertyConfigurator;
import org.sikessle.gameoflife.controller.GridController;
import org.sikessle.gameoflife.persistence.db4o.Db4oModule;
import org.sikessle.gameoflife.view.gui.SwingView;
import org.sikessle.gameoflife.view.tui.TextView;

import com.google.inject.Guice;
import com.google.inject.Injector;

public final class GameOfLife {

	private GameOfLife() {
	}

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");

		Injector injector = Guice.createInjector(new BaseModule(),
				new Db4oModule());
		GridController controller = injector.getInstance(GridController.class);
		new SwingView(controller);
		TextView textUI = new TextView(controller);
		textUI.readAndInterpretInLoopFromInputStream();
	}

}
