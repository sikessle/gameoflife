package org.sikessle.gameoflife.view.gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.sikessle.gameoflife.controller.GridController;

public class StatusPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1765005391707552689L;

	private final GridController controller;
	private JLabel status;

	public StatusPanel(GridController controller) {
		if (controller == null) {
			throw new NullPointerException();
		}

		this.controller = controller;
		buildStatusBar();
		controller.addObserver(this);
		updateStatus();
	}

	private void buildStatusBar() {
		status = new JLabel();
		add(status);
	}

	@Override
	public void update(Observable o, Object arg) {
		updateStatus();
	}

	private void updateStatus() {
		String strategy = controller.getGenerationStrategyName();
		status.setText("Generation Strategy: " + strategy);
	}
}
