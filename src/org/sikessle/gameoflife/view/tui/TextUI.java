package org.sikessle.gameoflife.view.tui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import org.sikessle.gameoflife.model.GridReadOnly;

public class TextUI implements Observer {

	private final GridReadOnly grid;
	private Scanner scanner;
	private PrintStream output;
	private final Command firstCommand;
	private Command lastCommand;

	public TextUI(GridReadOnly grid) {
		if (grid == null) {
			throw new NullPointerException();
		}
		this.grid = grid;
		firstCommand = lastCommand = new NullCommand();
		setDefaultStandardInputOutput();
		grid.addObserver(this);
		redraw();
	}

	private void setDefaultStandardInputOutput() {
		this.output = System.out;
		setInput(System.in);
	}

	public void setInput(InputStream input) {
		if (input == null) {
			throw new NullPointerException();
		}
		scanner = new Scanner(input);
	}

	public void setOutput(PrintStream output) {
		if (output == null) {
			throw new NullPointerException();
		}
		this.output = output;
	}

	@Override
	public void update(Observable o, Object arg) {
		redraw();
	}

	public void addCommand(Command command) {
		lastCommand.setSuccessorCommand(command);
		lastCommand = command;
	}

	public void redraw() {
		boolean[][] cells = grid.getCells();

		drawLineBreak();
		drawHorizontalBorder();
		for (int i = 0; i < cells.length; i++) {
			drawVerticalBorder();
			for (int j = 0; j < cells[i].length; j++) {
				drawCell(cells[i][j]);
			}
			drawVerticalBorder();
			drawLineBreak();
		}
		drawHorizontalBorder();
	}

	private void drawVerticalBorder() {
		writeOut("|");
	}

	private void drawHorizontalBorder() {
		int length = grid.getCells()[0].length;
		drawVerticalBorder();
		for (int i = 0; i < length; i++) {
			writeOut("-");
		}
		drawVerticalBorder();
		drawLineBreak();
	}

	private void drawCell(boolean alive) {
		String text;

		if (alive) {
			text = "#";
		} else {
			text = " ";
		}

		writeOut(text);
	}

	private void drawLineBreak() {
		writeOut(System.lineSeparator());
	}

	private void writeOut(String text) {
		output.print(text);
	}

	public void readAndInterpretInputLine() {
		if (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			interpretLine(line);
		}
	}

	private void interpretLine(String line) {
		String command = getCommand(line);
		Args arguments = getArguments(line);
		firstCommand.handleOrPassOnCommand(command, arguments);
	}

	private String getCommand(String line) {
		return line.split(" ")[0];
	}

	private Args getArguments(String line) {
		int indexOfFirstSpace = line.indexOf(" ");
		String argsPart;

		if (indexOfFirstSpace < 0) {
			argsPart = "";
		} else {
			argsPart = line.substring(indexOfFirstSpace);
		}
		return new Args(argsPart);
	}
}
