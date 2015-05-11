package edu.iis.mto.similarity;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;

import edu.iis.mto.search.dubler.SequenceSearcherDubler;

public class SimilarityFinderTest {

	private SequenceSearcherDubler sequenceSearcher;
	private SimilarityFinder similarityFinder;

	@Before
	public void onceExecutedBeforeAll() {
		sequenceSearcher = new SequenceSearcherDubler();
		similarityFinder = new SimilarityFinder(sequenceSearcher);
	}

	@Test
	public void calculateJackardSimilarity_zeroLengthSequences_shouldReturn1() {

		final int[] seq1 = new int[0];
		final int[] seq2 = new int[0];

		final double expected = 1.0;

		assertThat(similarityFinder.calculateJackardSimilarity(seq1, seq2),
				is(expected));
	}

	@Test
	public void calculateJackardSimilarity_TwoSameOneElementSequences_shouldReturn1() {

		final int[] sequence = new int[] { 1 };
		final double expected = 1.0;

		assertThat(
				similarityFinder.calculateJackardSimilarity(sequence, sequence),
				is(expected));
	}

}
