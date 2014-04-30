package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.figures.Figure;
import org.sikessle.gameoflife.figures.impl.Glider;

public class GliderCommand extends AbstractFigureCommand {

	public GliderCommand(TextView textUi) {
		super(textUi);
	}

	@Override
	protected Figure getFigure() {
		return new Glider();
	}

	@Override
	protected String getKey() {
		return "g";
	}
}
