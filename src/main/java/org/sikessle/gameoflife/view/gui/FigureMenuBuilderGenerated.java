package org.sikessle.gameoflife.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;

import org.sikessle.gameoflife.controller.GridController;
import org.sikessle.gameoflife.model.Figure;

import org.sikessle.gameoflife.model.impl.GliderFigure;
import org.sikessle.gameoflife.model.impl.RPentominoFigure;

public class FigureMenuBuilderGenerated {

	private final GridController controller;
	private final GridDrawingPanel gridPanel;

	public FigureMenuBuilderGenerated(GridController controller,
			GridDrawingPanel gridPanel) {
		if (controller == null || gridPanel == null) {
			throw new IllegalArgumentException();
		}

		this.controller = controller;
		this.gridPanel = gridPanel;
	}

	public List<Figure> getFigures() {
		List<Figure> figures = new ArrayList<Figure>();
		
		figures.add(new GliderFigure());
		figures.add(new RPentominoFigure());

		return figures;
	}

	public List<JMenuItem> buildFiguresMenu() {
		List<JMenuItem> menuItems = new ArrayList<>();

		JMenuItem figureItem;

		for (final Figure figure : getFigures()) {
			figureItem = new JMenuItem(figure.getName());
			figureItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new FigureSpawnOneTimeMouseAdapter(figure, controller,
							gridPanel);
				}
			});
			menuItems.add(figureItem);
		}

		return menuItems;
	}

}
