package org.sikessle.gameoflife.persistence.couchdb;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.sikessle.gameoflife.persistence.GridDto;
import org.sikessle.gameoflife.util.ArrayCopier;

public class GridCouchDBDto implements GridDto, Serializable {

	private static final long serialVersionUID = -4463411863364943008L;

	private String gameName;
	private String revision;
	private boolean[][] cells;
	private int steppedGenerations;

	@Override
	@JsonProperty("_id")
	public String getGameName() {
		return gameName;
	}

	@Override
	@JsonProperty("_id")
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@JsonProperty("_rev")
	public String getRevision() {
		return revision;
	}

	@JsonProperty("_rev")
	public void setRevision(String s) {
		revision = s;
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
