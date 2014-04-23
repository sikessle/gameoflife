package org.sikessle.gameoflife.view.tui;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Args implements Iterable<String> {

	String[] args;

	public Args(String inputToParse) {
		if (inputToParse == null) {
			throw new NullPointerException();
		}
		parse(inputToParse);
	}

	private void parse(String inputToParse) {
		parseAndTrim(inputToParse);
	}

	private void parseAndTrim(String inputToParse) {
		String[] rawArgs = inputToParse.trim().split(" ");
		if (rawArgs[0].isEmpty()) {
			args = new String[0];
			return;
		}
		args = new String[rawArgs.length];

		for (int i = 0; i < rawArgs.length; i++) {
			args[i] = rawArgs[i].trim();
		}
	}

	public int size() {
		return args.length;
	}

	@Override
	public Iterator<String> iterator() {
		return new ArgsIterator();
	}

	private class ArgsIterator implements Iterator<String> {

		private int nextIndex = 0;

		@Override
		public boolean hasNext() {
			return nextIndex < size();
		}

		@Override
		public String next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return args[nextIndex++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
