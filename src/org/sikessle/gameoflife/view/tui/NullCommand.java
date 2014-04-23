package org.sikessle.gameoflife.view.tui;

public class NullCommand extends Command {

	@Override
	public void handleOrPassOnCommand(String command, Args arguments) {
		passOnToSuccessor(command, arguments);
	}

	@Override
	public String toString() {
		return "";
	}
}
