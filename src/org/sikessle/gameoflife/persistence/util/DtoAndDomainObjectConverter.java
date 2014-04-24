package org.sikessle.gameoflife.persistence.util;

import org.sikessle.gameoflife.model.Grid;

import com.google.inject.Injector;

public class DtoAndDomainObjectConverter {

	private final Injector injector;

	public DtoAndDomainObjectConverter(Injector injector) {
		this.injector = injector;
	}

	public Grid convertDtoToDomain(GridDto gridDto) {
		Grid grid = injector.getInstance(Grid.class);
		grid.setCells(gridDto.getCells());

		return grid;
	}

	public GridDto convertDomainToDto(Grid grid, String gameName) {
		GridDto gridDto = new GridDto();
		gridDto.setGameName(gameName);
		gridDto.setCells(grid.getCells());

		return gridDto;
	}
}
