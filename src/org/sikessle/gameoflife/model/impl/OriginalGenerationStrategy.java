package org.sikessle.gameoflife.model.impl;

import org.sikessle.gameoflife.model.GenerationStrategyPlugin;

public class OriginalGenerationStrategy implements GenerationStrategyPlugin {

	@Override
	public String getName() {
		return "23/3-World";
	}

	@Override
	public boolean nextStateOfLivingCell(int livingNeighbors) {
		if (livingNeighbors < 2 || livingNeighbors > 3) {
			return false;
		}
		return true;
	}

	@Override
	public boolean nextStateOfDeadCell(int livingNeighbors) {
		if (livingNeighbors == 3) {
			return true;
		}
		return false;
	}

}
