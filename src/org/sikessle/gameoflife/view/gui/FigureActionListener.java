package org.sikessle.gameoflife.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.sikessle.gameoflife.controller.GridController;
import org.sikessle.gameoflife.model.Figure;

public class FigureActionListener implements ActionListener {

	private final Figure figure;
	private final GridController controller;
	private final GridDrawingPanel gridPanel;

	public FigureActionListener(Figure figure, GridController controller,
			GridDrawingPanel gridPanel) {
		if (figure == null || controller == null || gridPanel == null) {
			throw new IllegalArgumentException();
		}
		this.figure = figure;
		this.controller = controller;
		this.gridPanel = gridPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new FigureSpawnOneTimeMouseAdapter(figure, controller, gridPanel);
	}

}
