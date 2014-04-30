package org.sikessle.gameoflife.persistence.couchdb;

import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;
import org.ektorp.ViewResult.Row;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.persistence.GridDao;
import org.sikessle.gameoflife.persistence.util.DtoAndDomainObjectConverter;
import org.sikessle.gameoflife.persistence.util.GridDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

public class GridCouchDBDao implements GridDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(GridCouchDBDao.class);
	private final DtoAndDomainObjectConverter converter;
	private CouchDbConnector db = null;

	@Inject
	public GridCouchDBDao(Injector injector,
			@Named("couchDBServerUrl") String serverUrl) {
		converter = new DtoAndDomainObjectConverter(injector);
		initDB(serverUrl);
	}

	private void initDB(String serverUrl) {
		HttpClient client = null;
		try {
			client = new StdHttpClient.Builder().url(serverUrl).build();

		} catch (MalformedURLException e) {
			LOG.error(e.getMessage());
		}
		CouchDbInstance dbInstance = new StdCouchDbInstance(client);
		db = dbInstance.createConnector("gameoflife", true);
	}

	@Override
	public Grid getByName(String gameName) {
		if (gameName == null) {
			throw new IllegalArgumentException();
		}
		GridCouchDBDto found = findByName(gameName);

		if (found != null) {
			return converter.convertDtoToDomain(found);
		}
		return null;
	}

	@Override
	public boolean saveOrUpdate(Grid grid, String gameName) {
		if (grid == null || gameName == null) {
			throw new IllegalArgumentException();
		}
		GridDto gridDto = converter.convertDomainToDto(grid, gameName);
		updateOrCreate(gameName, gridDto);

		return true;
	}

	private void updateOrCreate(String gameName, GridDto gridDto) {
		if (db.contains(gameName)) {
			db.update(gridDto);
		} else {
			db.create(gridDto);
		}
	}

	@Override
	public boolean delete(String gameName) {
		if (gameName == null) {
			throw new IllegalArgumentException();
		}

		GridCouchDBDto found = findByName(gameName);
		if (found == null) {
			return false;
		}

		String revision = found.getRevision();
		db.delete(gameName, revision);

		return true;
	}

	@Override
	public List<String> getAllGameNames() {
		List<String> result = new LinkedList<String>();
		ViewQuery query = new ViewQuery().allDocs();
		ViewResult grids = db.queryView(query);

		for (Row row : grids) {
			result.add(row.getId());
		}

		return result;
	}

	private GridCouchDBDto findByName(final String gameName) {
		return db.find(GridCouchDBDto.class, gameName);
	}
}
