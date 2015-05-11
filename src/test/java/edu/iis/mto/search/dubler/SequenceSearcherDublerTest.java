package edu.iis.mto.search.dubler;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class SequenceSearcherDublerTest {

	@Test
	public void search_keyIsInSequence_shouldReturnTrue() {

		final int[] seq = new int[] { 1, 10, 20, 50, 100 };
		final int key = 100;
		final boolean expected = true;

		SequenceSearcherDubler sequenceSearcher = new SequenceSearcherDubler();

		assertThat(sequenceSearcher.search(key, seq).isFound(), is(expected));
	}

	@Test
	public void search_keyIsNotInSequence_shouldReturnFalse() {

		final int[] seq = new int[] { 1, 10, 20, 50, 100 };
		final int key = 25;
		final boolean expected = false;
		
		SequenceSearcherDubler sequenceSearcher = new SequenceSearcherDubler();

		assertThat(sequenceSearcher.search(key, seq).isFound(), is(expected));
	}
}
