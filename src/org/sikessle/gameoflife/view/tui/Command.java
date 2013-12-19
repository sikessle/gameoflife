package org.sikessle.gameoflife.view.tui;

public abstract class Command {

	private Command successor;

	public void setSuccessorCommand(Command successor) {
		this.successor = successor;
	}

	protected void passOnToSuccessor(String command, Args arguments) {
		if (successor != null) {
			successor.handleOrPassOnCommand(command, arguments);
		}
	}

	public abstract void handleOrPassOnCommand(String command, Args arguments);
}
