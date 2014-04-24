package org.sikessle.gameoflife.view.tui;

public class QuitCommand extends Command {

	private final TextView ui;
	private static final String DESCRIPTION = "q: quit";

	public QuitCommand(TextView ui) {
		if (ui == null) {
			throw new NullPointerException();
		}
		this.ui = ui;
	}

	@Override
	public void handleIfResponsible(String command, Args arguments) {
		if (command.equals("q")) {
			ui.quit();
		}
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}
}
