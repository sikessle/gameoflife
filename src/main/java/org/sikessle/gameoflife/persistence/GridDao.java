package org.sikessle.gameoflife.persistence;

import java.util.List;

import org.sikessle.gameoflife.model.Grid;

public interface GridDao {

	Grid getByName(String gameName);

	boolean saveOrUpdate(Grid grid, String gameName);

	boolean delete(String gameName);

	List<String> getAllGameNames();

}
