package org.sikessle.gameoflife.model.impl;

import org.sikessle.gameoflife.model.GenerationStrategyPlugin;

public class CopyWorldGenerationStrategy implements GenerationStrategyPlugin {

	private static final int MODULO_EVEN = 2;

	@Override
	public String getName() {
		return "Copy-World";
	}

	@Override
	public boolean nextStateOfLivingCell(int livingNeighbors) {
		if (isEven(livingNeighbors)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean nextStateOfDeadCell(int livingNeighbors) {
		if (!isEven(livingNeighbors)) {
			return true;
		}
		return false;
	}

	private boolean isEven(int number) {
		return number % MODULO_EVEN == 0;
	}

}
