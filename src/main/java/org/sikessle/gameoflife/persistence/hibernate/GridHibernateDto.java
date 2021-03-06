package org.sikessle.gameoflife.persistence.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.sikessle.gameoflife.persistence.GridDto;
import org.sikessle.gameoflife.util.ArrayCopier;

@Entity
@Table(name = "gameoflife_grid")
public class GridHibernateDto implements GridDto, Serializable {

	private static final long serialVersionUID = -4769267609179805493L;

	@Id
	private String gameName;
	@Column(columnDefinition = "LONGBLOB")
	private boolean[][] cells;

	private int steppedGenerations;

	@Override
	public String getGameName() {
		return gameName;
	}

	@Override
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@Override
	public boolean[][] getCells() {
		return ArrayCopier.copy(cells);
	}

	@Override
	public void setCells(boolean[][] cells) {
		this.cells = ArrayCopier.copy(cells);
	}

	@Override
	public int getNumberOfSteppedGenerations() {
		return steppedGenerations;
	}

	@Override
	public void setNumberOfSteppedGenerations(int generations) {
		steppedGenerations = generations;
	}

}
