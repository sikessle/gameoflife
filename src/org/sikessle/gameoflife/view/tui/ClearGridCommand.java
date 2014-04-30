package org.sikessle.gameoflife.view.tui;

public class ClearGridCommand extends Command {

	private final TextView ui;
	private static final String KEY = "c";
	private static final String DESCRIPTION = KEY + ": clear";

	public ClearGridCommand(TextView ui) {
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
		ui.getGridController().killAllCells();
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}
}
