package org.sikessle.gameoflife.model;

public interface GenerationStrategyPlugin {

	String getName();

	boolean nextStateOfLivingCell(int livingNeighbors);

	boolean nextStateOfDeadCell(int livingNeighbors);

}
