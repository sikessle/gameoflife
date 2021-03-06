package org.sikessle.gameoflife.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import org.sikessle.gameoflife.controller.GridController;

public class GridDrawingPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 4724075972585639540L;

	private final GridController controller;

	private final Color backgroundColor = new Color(0, 0, 0);
	private final Color livingCellColor = new Color(0, 176, 255);
	private final Color cellBorderColor = new Color(40, 40, 40);
	private Graphics graphics;

	private static final int CELL_SIZE = 30;
	private static final int BORDER_WIDTH = 1;

	public GridDrawingPanel(GridController controller) {
		if (controller == null) {
			throw new IllegalArgumentException();
		}

		this.controller = controller;
		setBackground(backgroundColor);
		MouseInputAdapter livingCellsSetter = new SetLivingCellsMouseListener(
				controller, CELL_SIZE);
		addMouseListener(livingCellsSetter);
		addMouseMotionListener(livingCellsSetter);
		controller.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		setSize(getPreferredSize());
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		int rows = controller.getNumberOfRows();
		int columns = controller.getNumberOfColumns();

		return new Dimension(columns * CELL_SIZE, rows * CELL_SIZE);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics = g;
		drawGrid();
	}

	private void drawGrid() {
		boolean[][] cells = controller.getCells();
		int x, y;

		y = 0;
		for (int i = 0; i < cells.length; i++) {
			x = 0;
			for (int j = 0; j < cells[i].length; j++) {
				drawCell(x, y, cells[i][j]);
				x += CELL_SIZE;
			}
			y += CELL_SIZE;
		}
	}

	private void drawCell(int x, int y, boolean alive) {
		drawCellBorder(x, y);

		if (alive) {
			drawLivingCell(x, y);
		}
	}

	private void drawCellBorder(int x, int y) {
		graphics.setColor(cellBorderColor);
		graphics.drawRect(x, y, CELL_SIZE, CELL_SIZE);
	}

	private void drawLivingCell(int x, int y) {
		graphics.setColor(livingCellColor);
		int startX = x + BORDER_WIDTH;
		int startY = y + BORDER_WIDTH;
		int size = CELL_SIZE - BORDER_WIDTH;
		graphics.fillRect(startX, startY, size, size);
	}

	public int getCellSize() {
		return CELL_SIZE;
	}

}
