package org.sikessle.gameoflife.view.tui;

import java.util.List;

public class ListSavedGamesCommand extends Command {

	private final TextView ui;
	private static final String KEY = "saved";
	private static final String DESCRIPTION = KEY + ": list saved games";

	public ListSavedGamesCommand(TextView ui) {
		if (ui == null) {
			throw new NullPointerException();
		}
		this.ui = ui;
	}

	@Override
	protected boolean isResponsible() {
		return getCommand().equals(KEY) && getArguments().size() == 0;
	}

	@Override
	public void execute() {
		ui.addLineToHeaderOutput("Saved Games:");
		for (String gameName : getSavedGameNames()) {
			ui.addLineToHeaderOutput(gameName);
		}
		ui.redraw();
	}

	private List<String> getSavedGameNames() {
		return ui.getGridController().listGames();
	}

	@Override
	public String toString() {
		return DESCRIPTION;
	}
}
