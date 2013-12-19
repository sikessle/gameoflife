package org.sikessle.gameoflife.view.tui;

import org.sikessle.gameoflife.controller.Controller;

public class QuitCommand extends Command {

	private final Controller controller;

	public QuitCommand(Controller controller) {
		if (controller == null) {
			throw new NullPointerException();
		}
		this.controller = controller;
	}

	@Override
	public void handleOrPassOnCommand(String command, Args arguments) {
		if (command.equals("q")) {
			controller.quit();
		} else {
			passOnToSuccessor(command, arguments);
		}
	}
}
