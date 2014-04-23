package org.sikessle.gameoflife.view.tui;

public abstract class Command {

	private Command successor;

	public void setSuccessorCommand(Command successor) {
		this.successor = successor;
	}

	public Command getSuccessor() {
		return successor;
	}

	private void passOnToSuccessor(String command, Args arguments) {
		if (successor != null) {
			successor.handle(command, arguments);
		}
	}

	public void handle(String command, Args arguments) {
		handleIfResponsible(command, arguments);
		passOnToSuccessor(command, arguments);
	}

	protected abstract void handleIfResponsible(String command, Args arguments);

	@Override
	public abstract String toString();
}
