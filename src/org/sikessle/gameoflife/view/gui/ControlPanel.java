package org.sikessle.gameoflife.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.sikessle.gameoflife.controller.GridController;

public class ControlPanel extends JPanel implements Observer {

	private static final long serialVersionUID = -5771602211891082511L;

	protected static final long GENERATIONS_DELAY = 100;

	private final GridController controller;

	private JButton stepNGenerationsBtn;
	private JButton stopStepNGenerationsBtn;
	private JLabel setGridSizeLabel;
	private JButton resetGrid;
	private JTextField stepNGenerationsField;
	private JTextField rowsField;
	private JTextField columnsField;

	public ControlPanel(GridController controller) {
		if (controller == null) {
			throw new NullPointerException();
		}

		this.controller = controller;

		buildControls();
		controller.addObserver(this);
		updateRowAndColumnFields();
	}

	private void buildControls() {
		buildStepNGenerationsControls();
		buildGridSizeControls();
		buildResetGridControls();

		addControlsToParent();
	}

	private void buildStepNGenerationsControls() {
		stepNGenerationsBtn = new JButton("step >");
		stopStepNGenerationsBtn = new JButton("stop");
		stepNGenerationsField = new JTextField(String.valueOf(1), 3);

		final StepNGenerationsAction stepGenerationsAction = new StepNGenerationsAction(
				controller, stepNGenerationsField);
		stepNGenerationsBtn.addActionListener(stepGenerationsAction);
		stopStepNGenerationsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stepGenerationsAction.stopIfRunning();
			}
		});
	}

	private void buildGridSizeControls() {
		setGridSizeLabel = new JLabel("grid size: ");
		rowsField = new JTextField(2);
		columnsField = new JTextField(2);

		rowsField.addActionListener(new SetGridSizeAction(controller,
				rowsField, columnsField));
		columnsField.addActionListener(new SetGridSizeAction(controller,
				rowsField, columnsField));
	}

	private void buildResetGridControls() {
		resetGrid = new JButton("clear");
		resetGrid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.killAllCells();
			}
		});
	}

	private void addControlsToParent() {
		JLabel rowColumnSeparator = new JLabel("x");

		add(resetGrid);
		add(stepNGenerationsBtn);
		add(stepNGenerationsField);
		add(stopStepNGenerationsBtn);
		add(setGridSizeLabel);
		add(rowsField);
		add(rowColumnSeparator);
		add(columnsField);
	}

	@Override
	public void update(Observable o, Object arg) {
		updateRowAndColumnFields();
	}

	private void updateRowAndColumnFields() {
		String rowsText = String.valueOf(controller.getNumberOfRows());
		String columnsText = String.valueOf(controller.getNumberOfColumns());

		rowsField.setText(rowsText);
		columnsField.setText(columnsText);
	}

}
