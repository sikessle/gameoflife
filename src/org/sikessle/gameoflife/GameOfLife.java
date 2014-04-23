package org.sikessle.gameoflife;

import org.sikessle.gameoflife.controller.impl.SwingUIController;
import org.sikessle.gameoflife.controller.impl.TextUIController;
import org.sikessle.gameoflife.model.BaseModule;
import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.plugin.PluginModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GameOfLife {

	public static void main(String[] args) {

		Injector injector = Guice.createInjector(new BaseModule(),
				new PluginModule());
		Grid grid = injector.getInstance(Grid.class);

		new SwingUIController(grid);
		new TextUIController(grid).startReadAndInterpretLoop();
	}
}
