package org.sikessle.gameoflife.persistence.util;

import static org.sikessle.gameoflife.persistence.util.CellCopyer.copyCells;

import org.sikessle.gameoflife.model.Grid;

import com.google.inject.Injector;

public class DtoAndDomainObjectConverter {

	private final Injector injector;

	public DtoAndDomainObjectConverter(Injector injector) {
		this.injector = injector;
	}

	public Grid convertDtoToDomain(GridDto gridDto) {
		Grid grid = injector.getInstance(Grid.class);
		boolean[][] cells = copyCells(gridDto.getCells());
		grid.setCells(cells);

		return grid;
	}

	public GridDto convertDomainToDto(Grid grid, String gameName) {
		GridDto gridDto = injector.getInstance(GridDto.class);
		boolean[][] cells = copyCells(grid.getCells());
		gridDto.setGameName(gameName);
		gridDto.setCells(cells);

		return gridDto;
	}
}
