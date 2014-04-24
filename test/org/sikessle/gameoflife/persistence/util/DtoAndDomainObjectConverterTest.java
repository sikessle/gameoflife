package org.sikessle.gameoflife.persistence.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.sikessle.gameoflife.BaseModule;
import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.persistence.db4o.Db4oModule;
import org.sikessle.gameoflife.persistence.db4o.GridDb4oDto;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class DtoAndDomainObjectConverterTest {

	private DtoAndDomainObjectConverter converter;

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new BaseModule(),
				new Db4oModule());
		converter = new DtoAndDomainObjectConverter(injector);
	}

	@Test
	public void convertDtoToDomain() {
		boolean[][] cells = { { true, true }, { false, true }, { false, false } };
		GridDto gridDto = new GridDb4oDto();
		gridDto.setCells(cells);
		Grid grid = converter.convertDtoToDomain(gridDto);
		boolean[][] domainCells = grid.getCells();

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				assertEquals(cells[i][j], domainCells[i][j]);
			}
		}
	}
}
