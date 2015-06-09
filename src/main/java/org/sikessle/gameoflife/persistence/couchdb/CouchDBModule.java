package org.sikessle.gameoflife.persistence.couchdb;

import org.sikessle.gameoflife.persistence.GridDao;
import org.sikessle.gameoflife.persistence.GridDto;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class CouchDBModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GridDao.class).to(GridCouchDBDao.class);
		bind(GridDto.class).to(GridCouchDBDto.class);
		bindConstant().annotatedWith(Names.named("couchDBServerUrl")).to(
				"http://lenny2.in.htwg-konstanz.de:5984");
	}

}
