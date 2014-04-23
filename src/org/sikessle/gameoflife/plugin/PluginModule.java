package org.sikessle.gameoflife.plugin;

import org.sikessle.gameoflife.plugin.impl.CopyWorldGenerationStrategy;

import com.google.inject.AbstractModule;

public class PluginModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GenerationStrategyPlugin.class).to(
				CopyWorldGenerationStrategy.class);
	}

}
