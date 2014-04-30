package org.sikessle.gameoflife.view.gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.sikessle.gameoflife.controller.GridController;

public class MenuBar extends JMenuBar implements Observer {

	private static final long serialVersionUID = 1765005391707552689L;

	private static final String TITLE_PERSISTENCE = "Load/Save";
	private static final String TITLE_LOAD = "Load";
	private static final String TITLE_SAVE = "Save";

	private final GridController controller;

	public MenuBar(GridController controller) {
		if (controller == null) {
			throw new IllegalArgumentException();
		}

		this.controller = controller;
		buildMenuBar();
		controller.addObserver(this);
		updateStatus();
	}

	private void buildMenuBar() {
		buildPersistenceMenu();
		buildGliderMenu();
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

	private void buildGliderMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable o, Object arg) {
		updateStatus();
	}

	private void updateStatus() {
	}
}
