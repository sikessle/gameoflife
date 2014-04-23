package org.sikessle.gameoflife.controller.impl;

import org.sikessle.gameoflife.controller.Controller;
import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.view.tui.GliderCommand;
import org.sikessle.gameoflife.view.tui.QuitCommand;
import org.sikessle.gameoflife.view.tui.SetGridSizeCommand;
import org.sikessle.gameoflife.view.tui.ToggleCellCommand;
import org.sikessle.gameoflife.view.tui.StepNGenerationsCommand;
import org.sikessle.gameoflife.view.tui.StepOneGenerationCommand;
import org.sikessle.gameoflife.view.tui.TextUI;

public class TextUIController extends Controller {

	private final TextUI textUI;
	private boolean runGame;

	public TextUIController(Grid grid) {
		super(grid);

		grid.setGridSize(10, 20);
		textUI = new TextUI(grid);
		addCommands();
		runGame = true;
		textUI.redraw();
	}

	private void addCommands() {
		textUI.addCommand(new QuitCommand(this));
		textUI.addCommand(new SetGridSizeCommand(this));
		textUI.addCommand(new StepOneGenerationCommand(this));
		textUI.addCommand(new StepNGenerationsCommand(this));
		textUI.addCommand(new ToggleCellCommand(this));
		textUI.addCommand(new GliderCommand(this));
	}

	public void startReadAndInterpretLoop() {
		while (runGame) {
			textUI.readAndInterpretFromInput();
		}
	}

	public void readAndInterpret(String line) {
		textUI.readAndInterpretFromArgument(line);
	}

	@Override
	public void quit() {
		runGame = false;
	}
}
