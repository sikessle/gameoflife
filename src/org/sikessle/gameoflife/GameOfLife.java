package org.sikessle.gameoflife;

import org.sikessle.gameoflife.controller.SwingUIController;
import org.sikessle.gameoflife.model.GenerationStrategy;
import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.model.GridImpl;
import org.sikessle.gameoflife.model.Living23Dead3GenerationStrategy;

public class GameOfLife {

	public static void main(String[] args) {
		GenerationStrategy generationStrategy = new Living23Dead3GenerationStrategy();
		Grid grid = new GridImpl(generationStrategy);
		new SwingUIController(grid);
		// new TextUIController(grid);
	}

}
