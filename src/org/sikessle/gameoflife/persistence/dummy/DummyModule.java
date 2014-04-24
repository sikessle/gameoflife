package org.sikessle.gameoflife.persistence.dummy;

import org.sikessle.gameoflife.persistence.GridDao;

import com.google.inject.AbstractModule;

public class DummyModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GridDao.class).to(GridDummyDao.class);
	}

}
