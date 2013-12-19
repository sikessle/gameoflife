package org.sikessle.gameoflife.model;


public class Living23Dead3GenerationStrategy extends GenerationStrategy {

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
