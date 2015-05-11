package edu.iis.mto.similarity;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import org.mockito.Matchers;
import edu.iis.mto.search.dubler.SearchResultDubler;
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

	@Test
	public void calculateJackardSimilarity_sameManyElementSequences_shouldReturn1() {

		final int[] seq = new int[] { 1, 10, 20, 50, 100 };
		final double expected = 1.0;

		assertThat(similarityFinder.calculateJackardSimilarity(seq, seq),
				is(expected));

	}

	@Test
	public void calculateJackardSimilarity_manyElementsCompletlyDifferentSequences_shouldReturn0() {

		final int[] seq1 = new int[] { 1, 10, 20, 50, 100 };
		final int[] seq2 = new int[] { 2, 17, 99, 101, 11, 142 };

		final double expected = 0.0;

		assertThat(similarityFinder.calculateJackardSimilarity(seq1, seq2),
				is(expected));
	}

	@Test
	public void calculateJackardSimilarity_manyElementsAndZeroLengthSequences_shouldReturn0() {

		final int[] seq1 = new int[] { 1, 10, 20, 50, 100 };
		final int[] seq2 = new int[0];

		final double expected = 0.0;

		assertThat(similarityFinder.calculateJackardSimilarity(seq1, seq2),
				is(expected));

	}

	@Test
	public void calculateJackardSimilarity_manyElements_oneDifferentElement_shouldReturnAlmost067() {

		final int[] seq1 = new int[] { 1, 10, 20, 50, 100 };
		final int[] seq2 = new int[] { 1, 10, 20, 50, 101 };

		final double expected = 0.67;
		final double error = 0.1;

		assertThat(similarityFinder.calculateJackardSimilarity(seq1, seq2),
				closeTo(expected, error));
	}

	@Test
	public void sequenceSearcherDubler_shouldBeCalledSeqLengthTimes() {

		SequenceSearcherDubler sequenceSearcher = mock(SequenceSearcherDubler.class);
		SimilarityFinder similarityFinder = new SimilarityFinder(
				sequenceSearcher);
		final int[] seq = new int[] { 1, 10, 20, 50, 100 };
		final int expected = seq.length;
		when(sequenceSearcher.search(anyInt(), Matchers.<int[]> any()))
				.thenReturn(new SearchResultDubler(0, true));

		similarityFinder.calculateJackardSimilarity(seq, seq);

		verify(sequenceSearcher, times(expected)).search(anyInt(),
				Matchers.<int[]> any());

	}
}
