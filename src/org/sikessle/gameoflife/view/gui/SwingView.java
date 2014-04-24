package org.sikessle.gameoflife.view.gui;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import org.sikessle.gameoflife.controller.GridController;

public class SwingView extends JFrame {

	private static final long serialVersionUID = 4452014221350020525L;

	private final GridController controller;

	private GridDrawingPanel gridPanel;

	private ControlPanel controlPanel;

	public SwingView(GridController controller) {
		if (controller == null) {
			throw new NullPointerException();
		}

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
		gridPanel = new GridDrawingPanel(controller);
		controlPanel = new ControlPanel(controller);
		add(controlPanel, BorderLayout.NORTH);
		add(gridPanel, BorderLayout.CENTER);

		gridPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				SwingView.this.pack();
			}
		});
	}

}
