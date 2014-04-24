package org.sikessle.gameoflife.view.tui;

public class ClearGridCommand extends Command {

	private final TextView ui;
	private static final String KEY = "c";
	private static final String DESCRIPTION = KEY + ": clear";

	public ClearGridCommand(TextView ui) {
		if (ui == null) {
			throw new NullPointerException();
		}
		this.ui = ui;
	}

	@Override
	protected boolean isResponsible() {
		return command.equals(KEY) && arguments.size() == 0;
	}

	@Override
	public void execute() {
		ui.getGridController().killAllCells();
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}
}
