package org.sikessle.gameoflife;

import org.sikessle.gameoflife.controller.impl.TextUIController;
import org.sikessle.gameoflife.model.BaseModule;
import org.sikessle.gameoflife.model.Grid;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GameOfLife {

	public static void main(String[] args) {

		Injector injector = Guice.createInjector(new BaseModule());
		Grid grid = injector.getInstance(Grid.class);

		// new SwingUIController(grid);
		new TextUIController(grid);
	}

}
