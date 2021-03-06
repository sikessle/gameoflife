package org.sikessle.gameoflife.persistence.util;

import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.persistence.GridDto;

import com.google.inject.Injector;

public class DtoAndDomainObjectConverter {

	private final Injector injector;

	public DtoAndDomainObjectConverter(Injector injector) {
		this.injector = injector;
	}

	public Grid convertDtoToDomain(GridDto gridDto) {
		Grid grid = injector.getInstance(Grid.class);
		boolean[][] cells = gridDto.getCells();
		grid.setCells(cells);
		grid.setNumberOfSteppedGenerations(gridDto
				.getNumberOfSteppedGenerations());

		return grid;
	}

	public GridDto convertDomainToDto(Grid grid, String gameName) {
		GridDto gridDto = injector.getInstance(GridDto.class);
		boolean[][] cells = grid.getCells();
		gridDto.setGameName(gameName);
		gridDto.setCells(cells);
		gridDto.setNumberOfSteppedGenerations(grid
				.getNumberOfSteppedGenerations());

		return gridDto;
	}
}
