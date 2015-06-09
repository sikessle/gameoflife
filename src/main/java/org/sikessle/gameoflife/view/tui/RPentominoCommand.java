package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.model.Figure;
import org.sikessle.gameoflife.model.impl.RPentomino;

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
