package org.sikessle.gameoflife.model;

public class AlwaysLivingGenerationStrategyPlugin implements GenerationStrategyPlugin {

	@Override
	public String getName() {
		return "Always Living";
	}

	@Override
	public boolean nextStateOfLivingCell(int livingNeighbors) {
		return true;
	}

	@Override
	public boolean nextStateOfDeadCell(int livingNeighbors) {
		return true;
	}

}
