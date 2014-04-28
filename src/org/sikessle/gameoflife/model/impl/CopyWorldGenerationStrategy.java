package org.sikessle.gameoflife.model.impl;

import org.sikessle.gameoflife.model.GenerationStrategyPlugin;

public class CopyWorldGenerationStrategy implements GenerationStrategyPlugin {

	@Override
	public String getName() {
		return "Copy-World";
	}

	@Override
	public boolean nextStateOfLivingCell(int livingNeighbors) {
		if (livingNeighbors % 2 == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean nextStateOfDeadCell(int livingNeighbors) {
		if (livingNeighbors % 2 != 0) {
			return true;
		}
		return false;
	}

}
