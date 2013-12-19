package org.sikessle.gameoflife.model;

public class AlwaysDeadGenerationStrategy extends GenerationStrategy {

	@Override
	protected boolean nextStateOfLivingCell(int livingNeighbors) {
		return false;
	}

	@Override
	protected boolean nextStateOfDeadCell(int livingNeighbors) {
		return false;
	}

}
