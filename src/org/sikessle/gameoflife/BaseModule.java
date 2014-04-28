package org.sikessle.gameoflife;

import org.sikessle.gameoflife.controller.GridController;
import org.sikessle.gameoflife.controller.impl.GridControllerImpl;
import org.sikessle.gameoflife.model.GenerationStrategyPlugin;
import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.model.impl.GridImpl;
import org.sikessle.gameoflife.model.impl.OriginalGenerationStrategy;

import com.google.inject.AbstractModule;

public class BaseModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Grid.class).to(GridImpl.class);
		bind(GenerationStrategyPlugin.class).to(
				OriginalGenerationStrategy.class);
		bind(GridController.class).to(GridControllerImpl.class);
	}

}
