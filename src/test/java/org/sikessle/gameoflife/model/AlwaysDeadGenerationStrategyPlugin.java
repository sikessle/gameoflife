package org.sikessle.gameoflife.model;

public class AlwaysDeadGenerationStrategyPlugin implements GenerationStrategyPlugin {

	@Override
	public String getName() {
		return "Always Dead";
	}

	@Override
	public boolean nextStateOfLivingCell(int livingNeighbors) {
		return false;
	}

	@Override
	public boolean nextStateOfDeadCell(int livingNeighbors) {
		return false;
	}

}
