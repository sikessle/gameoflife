package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.figures.Figure;
import org.sikessle.gameoflife.figures.impl.RPentomino;

public class RPentominoCommand extends AbstractFigureCommand {

	public RPentominoCommand(TextView textUi) {
		super(textUi);
	}

	@Override
	protected Figure getFigure() {
		return new RPentomino();
	}

	@Override
	protected String getKey() {
		return "rp";
	}

}
