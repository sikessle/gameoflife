package org.sikessle.gameoflife.view.tui;

public class NullCommand extends Command {

	@Override
	public void handleIfResponsible(String command, Args arguments) {
	}

	@Override
	public String toString() {
		return "";
	}
}
