package org.sikessle.gameoflife.model.impl;

import org.sikessle.gameoflife.model.GenerationStrategyPlugin;

public class CopyWorldGenerationStrategy extends GenerationStrategyPlugin {

	@Override
	public String getName() {
		return "Copy-World";
	}

	@Override
	protected boolean nextStateOfLivingCell(int livingNeighbors) {
		if (livingNeighbors % 2 == 0) {
			return false;
		}
		return true;
	}

	@Override
	protected boolean nextStateOfDeadCell(int livingNeighbors) {
		if (livingNeighbors % 2 != 0) {
			return true;
		}
		return false;
	}

}
