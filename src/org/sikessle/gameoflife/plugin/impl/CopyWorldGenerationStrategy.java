package org.sikessle.gameoflife.plugin.impl;

import org.sikessle.gameoflife.plugin.GenerationStrategyPlugin;

public class CopyWorldGenerationStrategy implements
		GenerationStrategyPlugin {

	@Override
	public String getName() {
		return "Kopierwelt";
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
