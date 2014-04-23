package org.sikessle.gameoflife.plugin.impl;

import org.sikessle.gameoflife.plugin.GenerationStrategyPlugin;

public class OriginalGenerationStrategy implements GenerationStrategyPlugin {

	@Override
	public String getName() {
		return "23/3-Welt";
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
