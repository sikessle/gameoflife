package org.sikessle.gameoflife.view.gui;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import org.sikessle.gameoflife.controller.Controller;
import org.sikessle.gameoflife.model.Grid;

public class SwingUI extends JFrame {

	private static final long serialVersionUID = 4452014221350020525L;

	private final Grid grid;
	private final Controller controller;

	public SwingUI(Grid grid, Controller controller) {
		if (grid == null || controller == null) {
			throw new NullPointerException();
		}

		this.grid = grid;
		this.controller = controller;
		buildGUI();
	}

	private void buildGUI() {
		setLayout(new BorderLayout());
		setResizable(false);

		addGUIComponents();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void addGUIComponents() {
		GridDrawingPanel gridPanel = new GridDrawingPanel(grid, controller);
		add(new ControlPanel(grid, controller), BorderLayout.NORTH);
		add(gridPanel, BorderLayout.CENTER);

		gridPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				SwingUI.this.pack();
			}
		});
	}

}
