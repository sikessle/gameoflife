package org.sikessle.gameoflife.view.tui;

public class NullCommand extends Command {

	@Override
	protected boolean isResponsible() {
		return false;
	}

	@Override
	protected void execute() {
	}

	@Override
	public String toString() {
		return "";
	}
}
