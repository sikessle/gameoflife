package org.sikessle.gameoflife.persistence.db4o;

import org.sikessle.gameoflife.persistence.GridDao;
import org.sikessle.gameoflife.persistence.GridDto;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class Db4oModule extends AbstractModule {

	@Override
	protected void configure() {
		bindConstant().annotatedWith(Names.named("db4oPath")).to(
				"/tmp/GameOfLifeDb4o");
		bind(GridDao.class).to(GridDb4oDao.class);
		bind(GridDto.class).to(GridDb4oDto.class);
	}

}
