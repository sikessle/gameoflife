package org.sikessle.gameoflife.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import org.sikessle.gameoflife.controller.GridController;

public class SetGridSizeAction implements ActionListener {

	private final JTextField rowsField;
	private final JTextField columnsField;
	private final GridController controller;

	public SetGridSizeAction(GridController controller, JTextField rowsField,
			JTextField columnsField) {
		if (controller == null || rowsField == null || columnsField == null) {
			throw new NullPointerException();
		}

		this.controller = controller;
		this.rowsField = rowsField;
		this.columnsField = columnsField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int rows = getCheckedNumberOfField(rowsField);
		int columns = getCheckedNumberOfField(columnsField);
		controller.setGridSize(rows, columns);
	}

	private int getCheckedNumberOfField(JTextField field) {
		int number = 0;

		try {
			number = Integer.parseInt(field.getText());
		} catch (NumberFormatException e) {
			// use default value
		}

		return number;
	}

}
