package org.sikessle.gameoflife.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.sikessle.gameoflife.controller.GridController;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1765005391707552689L;

	private static final String TITLE_PERSISTENCE = "Load/Save";
	private static final String TITLE_LOAD = "Load";
	private static final String TITLE_SAVE = "Save";
	private static final String TITLE_FIGURES = "Figures";

	private final GridController controller;

	private FigureMenuBuilderGenerated figureMenuBuilder;

	public MenuBar(GridController controller, GridDrawingPanel gridPanel) {
		if (controller == null || gridPanel == null) {
			throw new IllegalArgumentException();
		}

		this.controller = controller;
		this.figureMenuBuilder = new FigureMenuBuilderGenerated(controller, gridPanel);
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

		for (JMenuItem figureItem : figureMenuBuilder.buildFiguresMenu()) {
			figuresMenu.add(figureItem);
		}
		add(figuresMenu);
	}
}
