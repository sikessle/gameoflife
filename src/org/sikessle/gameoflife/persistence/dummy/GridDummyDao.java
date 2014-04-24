package org.sikessle.gameoflife.persistence.dummy;

import java.util.LinkedList;
import java.util.List;

import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.persistence.GridDao;

public class GridDummyDao implements GridDao {

	@Override
	public Grid getByName(String gameName) {
		return null;
	}

	@Override
	public boolean saveOrUpdate(Grid grid, String gameName) {
		return false;
	}

	@Override
	public boolean delete(String gameName) {
		return false;
	}

	@Override
	public List<String> getAllGameNames() {
		return new LinkedList<String>();
	}

}
