package org.sikessle.gameoflife.view.tui;

public abstract class Command {

	private Command successor;
	protected String command;
	protected Args arguments;

	public void setSuccessorCommand(Command successor) {
		this.successor = successor;
	}

	public Command getSuccessor() {
		return successor;
	}

	public void handle(String command, Args arguments) {
		this.command = command;
		this.arguments = arguments;
		if (isResponsible()) {
			execute();
		}
		passOnToSuccessor();
	}

	private void passOnToSuccessor() {
		if (successor != null) {
			successor.handle(command, arguments);
		}
	}

	protected abstract boolean isResponsible();

	protected abstract void execute();

	@Override
	public abstract String toString();
}
