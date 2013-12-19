package org.sikessle.gameoflife.model;

public class AlwaysLivingGenerationStrategy extends GenerationStrategy {

	@Override
	protected boolean nextStateOfLivingCell(int livingNeighbors) {
		return true;
	}

	@Override
	protected boolean nextStateOfDeadCell(int livingNeighbors) {
		return true;
	}

}
