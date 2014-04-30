package org.sikessle.gameoflife.persistence.db4o;

import java.util.LinkedList;
import java.util.List;

import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.persistence.GridDao;
import org.sikessle.gameoflife.persistence.util.DtoAndDomainObjectConverter;
import org.sikessle.gameoflife.persistence.util.GridDto;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

public class GridDb4oDao implements GridDao {

	private final EmbeddedObjectContainer db;
	private final DtoAndDomainObjectConverter converter;

	@Inject
	public GridDb4oDao(@Named("db4oPath") String dbPath, Injector injector) {
		db = Db4oEmbedded.openFile(dbPath);
		converter = new DtoAndDomainObjectConverter(injector);
	}

	@Override
	public Grid getByName(String gameName) {
		if (gameName == null) {
			throw new IllegalArgumentException();
		}
		GridDto found = findByName(gameName);
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
		// make sure only one object with the game name exists in the database
		delete(gameName);
		db.store(gridDto);
		return true;
	}

	@Override
	public boolean delete(String gameName) {
		if (gameName == null) {
			throw new IllegalArgumentException();
		}
		GridDto found = findByName(gameName);
		if (found != null) {
			db.delete(found);
			return true;
		}
		return false;
	}

	@Override
	public List<String> getAllGameNames() {
		List<String> result = new LinkedList<String>();
		ObjectSet<GridDb4oDto> grids = db.query(GridDb4oDto.class);

		for (GridDto grid : grids) {
			result.add(grid.getGameName());
		}

		return result;
	}

	@SuppressWarnings("serial")
	private GridDto findByName(final String gameName) {
		ObjectSet<GridDb4oDto> grids = db.query(new Predicate<GridDb4oDto>() {
			@Override
			public boolean match(GridDb4oDto grid) {
				return grid.getGameName().equals(gameName);
			}
		});

		if (grids.isEmpty()) {
			return null;
		}
		return grids.get(0);
	}

}
