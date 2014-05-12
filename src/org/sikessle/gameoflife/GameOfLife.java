package org.sikessle.gameoflife;

import org.apache.log4j.PropertyConfigurator;
import org.sikessle.gameoflife.controller.GridController;
import org.sikessle.gameoflife.persistence.dummy.DummyModule;
import org.sikessle.gameoflife.view.gui.SwingView;
import org.sikessle.gameoflife.view.tui.TextView;

import com.google.inject.Guice;
import com.google.inject.Injector;

public final class GameOfLife {

	private GameOfLife() {
	}

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		setSystemDependentUISettings();

		Injector injector = Guice.createInjector(new BaseModule(),
				new DummyModule());
		GridController controller = injector.getInstance(GridController.class);
		new SwingView(controller);
		TextView textUI = new TextView(controller);
		textUI.readAndInterpretInLoopFromInputStream();
	}

	private static void setSystemDependentUISettings() {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name",
				"GameOfLife");
	}

}
