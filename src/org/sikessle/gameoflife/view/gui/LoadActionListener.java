package org.sikessle.gameoflife.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.sikessle.gameoflife.controller.GridController;

public class LoadActionListener implements ActionListener {

	private final GridController controller;

	public LoadActionListener(GridController controller) {
		if (controller == null) {
			throw new IllegalArgumentException();
		}
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new LoadDialog(controller);
	}

}
