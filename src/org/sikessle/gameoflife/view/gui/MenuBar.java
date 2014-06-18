package org.sikessle.gameoflife.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.sikessle.gameoflife.controller.GridController;
import org.sikessle.gameoflife.model.Figure;
import org.sikessle.gameoflife.model.impl.Glider;
import org.sikessle.gameoflife.model.impl.LightWeightSpaceship;
import org.sikessle.gameoflife.model.impl.RPentomino;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1765005391707552689L;

	private static final String TITLE_PERSISTENCE = "Load/Save";
	private static final String TITLE_LOAD = "Load";
	private static final String TITLE_SAVE = "Save";
	private static final String TITLE_FIGURES = "Figures";

	private final GridController controller;
	private final GridDrawingPanel gridPanel;

	public MenuBar(GridController controller, GridDrawingPanel gridPanel) {
		if (controller == null || gridPanel == null) {
			throw new IllegalArgumentException();
		}

		this.controller = controller;
		this.gridPanel = gridPanel;
		buildMenuBar();
	}

	private void buildMenuBar() {
		buildPersistenceMenu();
		buildFiguresMenu();
	}

	private void buildPersistenceMenu() {
		JMenu persistenceMenu = new JMenu(TITLE_PERSISTENCE);
		JMenuItem loadItem = new JMenuItem(TITLE_LOAD);
		JMenuItem saveItem = new JMenuItem(TITLE_SAVE);

		loadItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoadDialog(controller);
			}
		});
		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SaveDialog(controller);
			}
		});

		persistenceMenu.add(loadItem);
		persistenceMenu.add(saveItem);
		add(persistenceMenu);
	}

	private void buildFiguresMenu() {
		JMenu figuresMenu = new JMenu(TITLE_FIGURES);

		for (final Figure figure : getFigures()) {
			JMenuItem figureItem = new JMenuItem(figure.getName());
			figureItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new FigureSpawnOneTimeMouseAdapter(figure, controller,
							gridPanel);
				}
			});
			figuresMenu.add(figureItem);
		}
		add(figuresMenu);
	}

	private List<Figure> getFigures() {
		List<Figure> figures = new ArrayList<Figure>();

		figures.add(new Glider());
		figures.add(new LightWeightSpaceship());
		figures.add(new RPentomino());

		return figures;
	}
}
