package org.sikessle.gameoflife.model;

import org.sikessle.gameoflife.model.impl.GridImpl;
import org.sikessle.gameoflife.model.impl.PluggableGenerationStrategy;

import com.google.inject.AbstractModule;

public class BaseModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Grid.class).to(GridImpl.class);
		bind(GenerationStrategy.class).to(PluggableGenerationStrategy.class);
	}

}
