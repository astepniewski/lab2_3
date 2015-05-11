package edu.iis.mto.search.dubler;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherDubler implements SequenceSearcher {

	public SearchResult search(int key, int[] seq) {
		boolean found = false;

		int i = 0;
		for (i = 0; i < seq.length; i++) {
			if (i == key) {
				found = true;
				break;
			}
		}
		return new SearchResultDubler(i, found);
	}

}
