package org.sikessle.gameoflife.view.tui;

public class QuitCommand extends Command {

	private final TextView ui;
	private static final String KEY = "q";
	private static final String DESCRIPTION = KEY + ": quit";

	public QuitCommand(TextView ui) {
		if (ui == null) {
			throw new IllegalArgumentException();
		}
		this.ui = ui;
	}

	@Override
	protected boolean isResponsible() {
		return getCommand().equals(KEY) && getArguments().size() == 0;
	}

	@Override
	public void execute() {
		ui.quit();
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}
}
