package org.sikessle.gameoflife.view.tui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.Scanner;

import org.sikessle.gameoflife.controller.GridController;

public class TextView implements Observer {

	private final GridController controller;
	private Scanner scanner;
	private PrintStream output;
	private Command firstCommand;
	private Command lastCommand;
	private boolean runGame = true;
	private final Queue<String> additionalHeaderOutput;

	public TextView(GridController controller) {
		if (controller == null) {
			throw new NullPointerException();
		}
		this.controller = controller;
		additionalHeaderOutput = new LinkedList<String>();
		addCommands();
		setDefaultInputOutput();
		controller.addObserver(this);
		redraw();
	}

	private void setDefaultInputOutput() {
		setOutput(System.out);
		setInput(System.in);
	}

	private void addCommands() {
		firstCommand = lastCommand = new NullCommand();
		addCommand(new QuitCommand(this));
		addCommand(new ClearGridCommand(this));
		addCommand(new ListSavedGamesCommand(this));
		addCommand(new LoadGameCommand(this));
		addCommand(new SaveGameCommand(this));
		addCommand(new SetGridSizeCommand(this));
		addCommand(new StepOneGenerationCommand(this));
		addCommand(new StepNGenerationsCommand(this));
		addCommand(new ToggleCellCommand(this));
		addCommand(new GliderCommand(this));
	}

	private void addCommand(Command command) {
		lastCommand.setSuccessorCommand(command);
		lastCommand = command;
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

	public void redraw() {
		boolean[][] cells = controller.getCells();

		drawLineBreak();
		drawAndFlushAdditionalHeaderOutput();
		drawLineBreak();
		drawGenerationStrategy();
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
		drawLineBreak();
		drawAvailableCommands();
	}

	private void drawAndFlushAdditionalHeaderOutput() {
		for (String header : additionalHeaderOutput) {
			writeOut(header);
			drawLineBreak();
		}
		additionalHeaderOutput.clear();
	}

	private void drawGenerationStrategy() {
		writeOut("Generation Strategy: ");
		writeOut(controller.getGenerationStrategyName());
	}

	public void addLineToHeaderOutput(String header) {
		if (header == null) {
			return;
		}
		additionalHeaderOutput.add(header);
	}

	private void drawVerticalBorder() {
		writeOut("|");
	}

	private void drawHorizontalBorder() {
		int length = controller.getNumberOfColumns();
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

	private void drawAvailableCommands() {
		List<String> commandFormats = getAllCommandFormats();
		writeOut("Commands: ");
		drawLineBreak();
		for (String command : commandFormats) {
			writeOut(command);
			drawLineBreak();
		}
	}

	private List<String> getAllCommandFormats() {
		Command command = firstCommand;
		List<String> commandFormats = new LinkedList<String>();

		while (command != null) {
			commandFormats.add(command.toString());
			command = command.getSuccessor();
		}

		return commandFormats;
	}

	private void writeOut(String text) {
		output.print(text);
	}

	public void readAndInterpretInLoopFromInputStream() {
		while (runGame) {
			readAndInterpretFromInputStream();
		}
	}

	/**
	 * Reads and interprets from the set input stream.
	 */
	public void readAndInterpretFromInputStream() {
		if (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			interpretLine(line);
		}
	}

	/**
	 * Reads and interprets from the argument.
	 * 
	 * @param line
	 */
	public void readAndInterpretFromArgument(String line) {
		if (line == null) {
			return;
		}
		interpretLine(line);
	}

	private void interpretLine(String line) {
		String command = getCommand(line);
		Args arguments = getArguments(line);
		firstCommand.handle(command, arguments);
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

	public void quit() {
		runGame = false;
	}

	public GridController getGridController() {
		return controller;
	}
}
