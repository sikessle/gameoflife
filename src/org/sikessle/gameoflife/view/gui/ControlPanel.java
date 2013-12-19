package org.sikessle.gameoflife.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.sikessle.gameoflife.controller.Controller;
import org.sikessle.gameoflife.model.GridReadOnly;

public class ControlPanel extends JPanel implements Observer {

	private static final long serialVersionUID = -5771602211891082511L;

	protected static final long GENERATIONS_DELAY = 100;

	private final GridReadOnly grid;
	private final Controller controller;

	private JButton stepNGenerationsBtn;
	private JButton stopStepNGenerationsBtn;
	private JButton setGridSizeBtn;
	private JButton resetGrid;
	private JTextField stepNGenerationsField;
	private JTextField rowsField;
	private JTextField columnsField;

	public ControlPanel(GridReadOnly grid, Controller controller) {
		if (grid == null || controller == null) {
			throw new NullPointerException();
		}

		this.grid = grid;
		this.controller = controller;

		buildControls();
		grid.addObserver(this);
	}

	private void buildControls() {
		buildStepNGenerationsControls();
		buildGridSizeControls();
		buildResetGridControls();

		addControlsToParent();
	}

	private void buildStepNGenerationsControls() {
		stepNGenerationsBtn = new JButton("Zeige Generationen");
		stopStepNGenerationsBtn = new JButton("Stop");
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
		setGridSizeBtn = new JButton("Gittergr\u00F6\u00dfe setzen:");
		rowsField = new JTextField(2);
		columnsField = new JTextField(2);

		setGridSizeBtn.addActionListener(new SetGridSizeAction(controller,
				rowsField, columnsField));
	}

	private void buildResetGridControls() {
		resetGrid = new JButton("Reset");
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
		add(stepNGenerationsField);
		add(stepNGenerationsBtn);
		add(stopStepNGenerationsBtn);
		add(setGridSizeBtn);
		add(rowsField);
		add(rowColumnSeparator);
		add(columnsField);
	}

	@Override
	public void update(Observable o, Object arg) {
		String rowsText = String.valueOf(grid.getNumberOfRows());
		String columnsText = String.valueOf(grid.getNumberOfColumns());

		rowsField.setText(rowsText);
		columnsField.setText(columnsText);
	}

}
