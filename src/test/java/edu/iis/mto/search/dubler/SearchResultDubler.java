package edu.iis.mto.search.dubler;

import edu.iis.mto.search.SearchResult;

public class SearchResultDubler implements SearchResult {

	private int position;
	private boolean found;

	public SearchResultDubler(int position, boolean found) {
		super();
		this.position = position;
		this.found = found;
	}

	public boolean isFound() {
		return found;
	}

	public int getPosition() {
		return position;
	}

}
