package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.figures.Figure;
import org.sikessle.gameoflife.figures.impl.LightWeightSpaceship;

public class LightWeightSpaceshipCommand extends AbstractFigureCommand {

	public LightWeightSpaceshipCommand(TextView textUi) {
		super(textUi);
	}

	@Override
	protected Figure getFigure() {
		return new LightWeightSpaceship();
	}

	@Override
	protected String getKey() {
		return "lws";
	}

}
