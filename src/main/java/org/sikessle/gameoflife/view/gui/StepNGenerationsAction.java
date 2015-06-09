package org.sikessle.gameoflife.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;

import org.sikessle.gameoflife.controller.GridController;

public class StepNGenerationsAction implements ActionListener {

	private final GridController controller;
	private final JTextField textField;

	private int numberOfGenerations;
	private static final int GENERATIONS_DELAY = 100;
	private Timer generationsTimer;

	public StepNGenerationsAction(GridController controller,
			JTextField textField) {
		if (controller == null || textField == null) {
			throw new IllegalArgumentException();
		}

		this.controller = controller;
		this.textField = textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setNumberOfGenerations();
		stopIfRunning();
		startGenerationsTimerWithThread();
	}

	private boolean isGenerationsTimerRunning() {
		return generationsTimer != null;
	}

	private void cancelGenerationsTimer() {
		generationsTimer.cancel();
		generationsTimer = null;
	}

	private void startGenerationsTimerWithThread() {
		TimerTask generationsTask = new GenerationsTimerTask();
		generationsTimer = new Timer(true);
		generationsTimer.scheduleAtFixedRate(generationsTask, 0,
				GENERATIONS_DELAY);
	}

	private class GenerationsTimerTask extends TimerTask {
		private int generationsCount;

		@Override
		public void run() {
			if (generationsCount >= numberOfGenerations) {
				cancelGenerationsTimer();
			} else {
				controller.stepOneGeneration();
				generationsCount++;
			}
		}
	}

	public void stopIfRunning() {
		if (isGenerationsTimerRunning()) {
			cancelGenerationsTimer();
		}
	}

	private void setNumberOfGenerations() {
		numberOfGenerations = getCheckedNumberOfField();
	}

	private int getCheckedNumberOfField() {
		int number = 0;

		try {
			number = Integer.parseInt(textField.getText());
		} catch (NumberFormatException e) {
			// use default value
		}

		return number;
	}

}
