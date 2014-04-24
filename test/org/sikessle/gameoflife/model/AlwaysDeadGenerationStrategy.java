package org.sikessle.gameoflife.model;

public class AlwaysDeadGenerationStrategy extends GenerationStrategyPlugin {

	@Override
	public String getName() {
		return "Always Dead";
	}

	@Override
	protected boolean nextStateOfLivingCell(int livingNeighbors) {
		return false;
	}

	@Override
	protected boolean nextStateOfDeadCell(int livingNeighbors) {
		return false;
	}

}
