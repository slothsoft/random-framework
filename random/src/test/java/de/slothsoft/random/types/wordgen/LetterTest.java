package de.slothsoft.random.types.wordgen;

import org.junit.Assert;
import org.junit.Test;

public class LetterTest {

	private static final double DELTA = 0.0001;

	final Letter letter = new Letter('?');

	@Test
	public void testEquals() throws Exception {
		assertEquals(new Letter('c'), new Letter('c'));
		assertEquals(new Letter('c').probability(0.5), new Letter('c').probability(0.5));
	}

	private static void assertEquals(Letter expected, Letter actual) {
		final String error = "Letter has wrong equals() / hashcode(): " + actual;

		Assert.assertEquals(error, expected, actual);
		Assert.assertEquals(error, expected.hashCode(), actual.hashCode());
	}

	@Test
	public void testNotEquals() throws Exception {
		assertNotEquals(new Letter('c'), new Letter('d'));
		assertNotEquals(new Letter('c').probability(0.5), new Letter('c').probability(0.4));
		assertNotEquals(new Letter('c').probability(0.5), new Letter('c').probability(0.500001));
		assertNotEquals(new Letter('d').probability(0.4), new Letter('c').probability(0.4));
	}

	private static void assertNotEquals(Letter expected, Letter actual) {
		final String error = "Letter has wrong equals() / hashcode(): " + actual;

		Assert.assertNotEquals(error, expected, actual);
		Assert.assertNotEquals(error, expected.hashCode(), actual.hashCode());
	}

	@Test
	public void testEqualsSpecial() throws Exception {
		final String error = "Letter has wrong equals() method: " + this.letter;

		Assert.assertTrue(error, this.letter.equals(this.letter));

		Assert.assertFalse(error, this.letter.equals(null));
		Assert.assertFalse(error, this.letter.equals(new Object()));
	}

	@Test
	public void testProbability() throws Exception {
		this.letter.setProbability(0.5);
		Assert.assertEquals(0.5, this.letter.getProbability(), DELTA);

		this.letter.probability(0.7);
		Assert.assertEquals(0.7, this.letter.getProbability(), DELTA);
	}
}
