package org.sikessle.gameoflife.persistence;

import java.util.List;

import org.sikessle.gameoflife.model.Grid;

public interface GridDao {

	Grid getByName(String gameName);

	void saveOrUpdate(Grid grid, String gameName);

	void delete(String gameName);

	List<String> getAllGameNames();

}
