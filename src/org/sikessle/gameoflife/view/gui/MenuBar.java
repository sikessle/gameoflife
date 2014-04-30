package org.sikessle.gameoflife.view.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.sikessle.gameoflife.controller.GridController;
import org.sikessle.gameoflife.figures.Figure;
import org.sikessle.gameoflife.figures.impl.Glider;
import org.sikessle.gameoflife.figures.impl.LightWeightSpaceship;
import org.sikessle.gameoflife.figures.impl.RPentomino;

public class MenuBar extends JMenuBar implements Observer {

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
		controller.addObserver(this);
		updateStatus();
	}

	private void buildMenuBar() {
		buildPersistenceMenu();
		buildFiguresMenu();
	}

	private void buildPersistenceMenu() {
		JMenu persistenceMenu = new JMenu(TITLE_PERSISTENCE);
		JMenuItem loadItem = new JMenuItem(TITLE_LOAD);
		JMenuItem saveItem = new JMenuItem(TITLE_SAVE);

		loadItem.addActionListener(new LoadActionListener(controller));
		saveItem.addActionListener(new SaveActionListener(controller));

		persistenceMenu.add(loadItem);
		persistenceMenu.add(saveItem);
		add(persistenceMenu);
	}

	private void buildFiguresMenu() {
		JMenu figuresMenu = new JMenu(TITLE_FIGURES);

		for (Figure figure : getFigures()) {
			JMenuItem figureItem = new JMenuItem(figure.getName());
			figureItem.addActionListener(new FigureActionListener(figure,
					controller, gridPanel));
			figuresMenu.add(figureItem);
		}
		add(figuresMenu);
	}

	private List<Figure> getFigures() {
		List<Figure> figures = new LinkedList<Figure>();

		figures.add(new Glider());
		figures.add(new LightWeightSpaceship());
		figures.add(new RPentomino());

		return figures;
	}

	@Override
	public void update(Observable o, Object arg) {
		updateStatus();
	}

	private void updateStatus() {
	}
}
