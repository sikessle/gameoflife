package org.sikessle.gameoflife.model.impl;

import org.sikessle.gameoflife.model.GenerationStrategyPlugin;

public class OriginalGenerationStrategy implements GenerationStrategyPlugin {

	private static final int ALIVE_TO_DEAD_MIN_NEIGHBORS = 2;
	private static final int ALIVE_TO_DEAD_MAX_NEIHBORS = 3;
	private static final int DEAD_TO_ALIVE_NEIGHBORS = 3;

	@Override
	public String getName() {
		return "23/3-World";
	}

	@Override
	public boolean nextStateOfLivingCell(int livingNeighbors) {
		if (livingNeighbors < ALIVE_TO_DEAD_MIN_NEIGHBORS
				|| livingNeighbors > ALIVE_TO_DEAD_MAX_NEIHBORS) {
			return false;
		}
		return true;
	}

	@Override
	public boolean nextStateOfDeadCell(int livingNeighbors) {
		if (livingNeighbors == DEAD_TO_ALIVE_NEIGHBORS) {
			return true;
		}
		return false;
	}

}
