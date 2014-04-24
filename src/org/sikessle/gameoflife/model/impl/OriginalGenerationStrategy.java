package org.sikessle.gameoflife.model.impl;

import org.sikessle.gameoflife.model.GenerationStrategyPlugin;

public class OriginalGenerationStrategy extends GenerationStrategyPlugin {

	@Override
	public String getName() {
		return "23/3-World";
	}

	@Override
	protected boolean nextStateOfLivingCell(int livingNeighbors) {
		if (livingNeighbors < 2 || livingNeighbors > 3) {
			return false;
		}
		return true;
	}

	@Override
	protected boolean nextStateOfDeadCell(int livingNeighbors) {
		if (livingNeighbors == 3) {
			return true;
		}
		return false;
	}

}
