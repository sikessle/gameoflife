package org.sikessle.gameoflife.model;

public class AlwaysLivingGenerationStrategy extends GenerationStrategyPlugin {

	@Override
	public String getName() {
		return "Always Living";
	}

	@Override
	protected boolean nextStateOfLivingCell(int livingNeighbors) {
		return true;
	}

	@Override
	protected boolean nextStateOfDeadCell(int livingNeighbors) {
		return true;
	}

}
