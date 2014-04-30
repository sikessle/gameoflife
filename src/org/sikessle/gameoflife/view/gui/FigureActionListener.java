package org.sikessle.gameoflife.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.sikessle.gameoflife.controller.GridController;
import org.sikessle.gameoflife.model.Figure;

public class FigureActionListener extends MouseAdapter implements
		ActionListener {

	private final Figure figure;
	private final GridController controller;
	private final GridDrawingPanel gridPanel;
	private int cellSize = 30;

	public FigureActionListener(Figure figure, GridController controller,
			GridDrawingPanel gridPanel) {
		if (figure == null || controller == null || gridPanel == null) {
			throw new IllegalArgumentException();
		}
		this.figure = figure;
		this.controller = controller;
		this.gridPanel = gridPanel;
		this.cellSize = gridPanel.getCellSize();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gridPanel.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = e.getY() / cellSize;
		int column = e.getX() / cellSize;

		// remove clicked cell
		controller.setCellToDeadAtPosition(row, column);

		controller.spawnFigure(figure, row, column);

		gridPanel.removeMouseListener(this);
	}

}
