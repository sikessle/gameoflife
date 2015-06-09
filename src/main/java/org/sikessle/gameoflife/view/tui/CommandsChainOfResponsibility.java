package org.sikessle.gameoflife.view.tui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CommandsChainOfResponsibility {

	private final TextView textUi;
	private final List<Command> commands;

	public CommandsChainOfResponsibility(TextView textUi) {
		if (textUi == null) {
			throw new IllegalArgumentException();
		}
		this.textUi = textUi;
		commands = new ArrayList<Command>();
		addCommands();
	}

	private void addCommands() {
		commands.add(new QuitCommand(textUi));
		commands.add(new ClearGridCommand(textUi));
		commands.add(new ListSavedGamesCommand(textUi));
		commands.add(new LoadGameCommand(textUi));
		commands.add(new SaveGameCommand(textUi));
		commands.add(new SetGridSizeCommand(textUi));
		commands.add(new StepOneGenerationCommand(textUi));
		commands.add(new StepNGenerationsCommand(textUi));
		commands.add(new ToggleCellCommand(textUi));
	}

	public void handle(String command, Args arguments) {
		for (Command cmd : commands) {
			cmd.handle(command, arguments);
		}
	}

	public List<String> getAllCommandDescriptions() {
		List<String> descriptions = new LinkedList<String>();
		for (Command cmd : commands) {
			descriptions.add(cmd.toString());
		}
		return descriptions;
	}

}
