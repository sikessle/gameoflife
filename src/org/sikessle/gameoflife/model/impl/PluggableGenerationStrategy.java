package org.sikessle.gameoflife.model.impl;

import org.sikessle.gameoflife.model.GenerationStrategy;
import org.sikessle.gameoflife.plugin.GenerationStrategyPlugin;

import com.google.inject.Inject;

public class PluggableGenerationStrategy extends GenerationStrategy {

	private final GenerationStrategyPlugin generationStrategy;

	@Inject
	public PluggableGenerationStrategy(GenerationStrategyPlugin strategy) {
		this.generationStrategy = strategy;
	}

	@Override
	protected boolean nextStateOfLivingCell(int livingNeighbors) {
		return generationStrategy.nextStateOfLivingCell(livingNeighbors);
	}

	@Override
	protected boolean nextStateOfDeadCell(int livingNeighbors) {
		return generationStrategy.nextStateOfDeadCell(livingNeighbors);
	}

}
