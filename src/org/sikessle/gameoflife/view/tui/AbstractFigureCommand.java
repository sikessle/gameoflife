package org.sikessle.gameoflife.view.tui;

import java.util.Iterator;
import java.util.List;

import org.sikessle.gameoflife.controller.GridController;
import org.sikessle.gameoflife.figures.Coordinate;
import org.sikessle.gameoflife.figures.Figure;
import org.sikessle.gameoflife.figures.impl.CoordinateTranslator;

public abstract class AbstractFigureCommand extends Command {

	private final GridController controller;
	private int spawnX = -1;
	private int spawnY = -1;

	public AbstractFigureCommand(TextView textUi) {
		if (textUi == null) {
			throw new IllegalArgumentException();
		}
		this.controller = textUi.getGridController();
	}

	@Override
	protected boolean isResponsible() {
		parseArguments();
		return getCommand().equals(getKey()) && argsAreValid();
	}

	private void parseArguments() {
		if (getArguments().size() < 2) {
			return;
		}

		Iterator<String> iterator = getArguments().iterator();
		spawnX = getCheckedNumber(iterator.next());
		spawnY = getCheckedNumber(iterator.next());
	}

	private int getCheckedNumber(String number) {
		int result = -1;
		try {
			result = Integer.valueOf(number);
		} catch (NumberFormatException e) {

		}
		return result;
	}

	private boolean argsAreValid() {
		return getArguments().size() == 2 && spawnX != -1 && spawnY != -1;
	}

	@Override
	public void execute() {
		List<Coordinate> source = getFigure().getCoordinates();
		List<Coordinate> coordinates = CoordinateTranslator
				.translatePositiveBy(source, spawnX, spawnY);

		for (Coordinate coord : coordinates) {
			controller.setCellToLivingAtPosition(coord.getX(), coord.getY());
		}
	}

	protected abstract Figure getFigure();

	protected abstract String getKey();

	@Override
	public String toString() {
		return getKey() + " [x] [y]: generate " + getFigure().getName()
				+ " at position x,y";
	}

}
