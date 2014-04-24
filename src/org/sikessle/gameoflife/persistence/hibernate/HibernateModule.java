package org.sikessle.gameoflife.persistence.hibernate;

import org.sikessle.gameoflife.persistence.GridDao;
import org.sikessle.gameoflife.persistence.util.GridDto;

import com.google.inject.AbstractModule;

public class HibernateModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GridDao.class).to(GridHibernateDao.class);
		bind(GridDto.class).to(GridHibernateDto.class);
	}

}
