package org.sikessle.gameoflife.controller.impl;

import org.sikessle.gameoflife.controller.Controller;
import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.view.tui.DemoGliderCommand;
import org.sikessle.gameoflife.view.tui.QuitCommand;
import org.sikessle.gameoflife.view.tui.SetGridSizeCommand;
import org.sikessle.gameoflife.view.tui.SetLivingCellCommand;
import org.sikessle.gameoflife.view.tui.StepNGenerationsCommand;
import org.sikessle.gameoflife.view.tui.StepOneGenerationCommand;
import org.sikessle.gameoflife.view.tui.TextUI;

public class TextUIController extends Controller {

	private final TextUI consoleUI;
	private boolean runGame;

	public TextUIController(Grid grid) {
		super(grid);

		grid.setGridSize(10, 20);
		consoleUI = new TextUI(grid);
		addCommands();
		consoleUI.redraw();
		runGame = true;
		consoleUI.redraw();
	}

	private void addCommands() {
		consoleUI.addCommand(new QuitCommand(this));
		consoleUI.addCommand(new SetGridSizeCommand(this));
		consoleUI.addCommand(new StepOneGenerationCommand(this));
		consoleUI.addCommand(new StepNGenerationsCommand(this));
		consoleUI.addCommand(new SetLivingCellCommand(this));
		consoleUI.addCommand(new DemoGliderCommand(this));
	}

	public void readAndInterpretInputInLoop() {
		while (runGame) {
			consoleUI.readAndInterpretInputLine();
		}
	}

	public void readAndInterpretInput(String line) {
		consoleUI.readAndInterpretInput(line);
	}

	@Override
	public void quit() {
		runGame = false;
	}
}
