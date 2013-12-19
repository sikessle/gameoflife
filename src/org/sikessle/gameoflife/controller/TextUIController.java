package org.sikessle.gameoflife.controller;

import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.view.tui.AnimateCommand;
import org.sikessle.gameoflife.view.tui.DemoGliderCommand;
import org.sikessle.gameoflife.view.tui.QuitCommand;
import org.sikessle.gameoflife.view.tui.SetGridSizeCommand;
import org.sikessle.gameoflife.view.tui.SetLivingCellCommand;
import org.sikessle.gameoflife.view.tui.StepOneGenerationCommand;
import org.sikessle.gameoflife.view.tui.TextUI;

public class TextUIController extends Controller {

	private final TextUI consoleUI;
	private boolean runGame;

	public TextUIController(Grid grid) {
		super(grid);

		consoleUI = new TextUI(grid);
		addCommands();
		runGame = true;
		runUILoop();
	}

	private void addCommands() {
		consoleUI.addCommand(new QuitCommand(this));
		consoleUI.addCommand(new SetGridSizeCommand(this));
		consoleUI.addCommand(new AnimateCommand(this));
		consoleUI.addCommand(new SetLivingCellCommand(this));
		consoleUI.addCommand(new StepOneGenerationCommand(this));
		consoleUI.addCommand(new DemoGliderCommand(this));
	}

	private void runUILoop() {
		while (runGame) {
			consoleUI.readAndInterpretInputLine();
		}
	}

	@Override
	public void quit() {
		runGame = false;
	}
}
