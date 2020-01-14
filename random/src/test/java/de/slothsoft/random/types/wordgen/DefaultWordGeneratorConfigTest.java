package de.slothsoft.random.types.wordgen;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class DefaultWordGeneratorConfigTest {

	private static final double DELTA = 0.0001;

	DefaultWordGeneratorConfig config = new DefaultWordGeneratorConfig();

	@Test
	public void testEquals() throws Exception {
		assertEquals(new DefaultWordGeneratorConfig(), new DefaultWordGeneratorConfig());
		assertEquals(new DefaultWordGeneratorConfig().letters(new Letter('a')),
				new DefaultWordGeneratorConfig().letters(new Letter('a')));
		assertEquals(new DefaultWordGeneratorConfig().standardWordLength(1),
				new DefaultWordGeneratorConfig().standardWordLength(1));
		assertEquals(new DefaultWordGeneratorConfig().letters(new Letter('a')).standardWordLength(2),
				new DefaultWordGeneratorConfig().letters(new Letter('a')).standardWordLength(2));
	}

	private static void assertEquals(DefaultWordGeneratorConfig expected, DefaultWordGeneratorConfig actual) {
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expected.hashCode(), actual.hashCode());
	}

	@Test
	public void testNotEquals() throws Exception {
		assertNotEquals(new DefaultWordGeneratorConfig().letters(new Letter('a')),
				new DefaultWordGeneratorConfig().letters(new Letter('b')));
		assertNotEquals(new DefaultWordGeneratorConfig().standardWordLength(1),
				new DefaultWordGeneratorConfig().standardWordLength(2));
		assertNotEquals(new DefaultWordGeneratorConfig().letters(new Letter('a')).standardWordLength(1),
				new DefaultWordGeneratorConfig().letters(new Letter('b')).standardWordLength(2));
	}

	private static void assertNotEquals(DefaultWordGeneratorConfig expected, DefaultWordGeneratorConfig actual) {
		Assert.assertNotEquals(expected, actual);
		Assert.assertNotEquals(expected.hashCode(), actual.hashCode());
	}

	@Test
	public void testEqualsSpecial() throws Exception {
		final String error = "Config has wrong equals() method: " + this.config;
		Assert.assertTrue(error, this.config.equals(this.config));

		Assert.assertFalse(error, this.config.equals(null));
		Assert.assertFalse(error, this.config.equals(new Object()));
	}

	@Test
	public void testSupportedLetters() throws Exception {
		final Letter[] letters1 = {new Letter('a'), new Letter('b').probability(2), new Letter('c').probability(0.5)};
		this.config.setSupportedLetters(letters1);
		Assert.assertArrayEquals(letters1, this.config.getLetters());

		final Letter[] letters2 = {new Letter('d').probability(0.1), new Letter('e'), new Letter('f').probability(100)};
		this.config.letters(letters2);
		Assert.assertArrayEquals(letters2, this.config.getLetters());
	}

	@Test
	public void testSupportedLettersNull() throws Exception {
		final Letter[] letters = new Letter[0];
		try {
			this.config.setSupportedLetters(letters);
			Assert.fail("There should be an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertNotNull(e);
		}
	}

	@Test
	public void testStandardWordLength() throws Exception {
		this.config.setStandardWordLength(1);
		Assert.assertEquals(1, this.config.getStandardWordLength(), DELTA);

		this.config.standardWordLength(2);
		Assert.assertEquals(2, this.config.getStandardWordLength(), DELTA);
	}

	@Test
	public void testAddLetter() throws Exception {
		this.config.letters = new ArrayList<>();

		this.config.addLetters(new Letter('a').probability(0.1));
		Assert.assertArrayEquals(new char[]{'a'}, this.config.getSupportedCharacters());
		Assert.assertEquals(0.1, this.config.getProbability('a'), DELTA);

		this.config.addLetters(new Letter('b').probability(0.2), new Letter('c').probability(0.3));
		Assert.assertArrayEquals(new char[]{'a', 'b', 'c'}, this.config.getSupportedCharacters());
		Assert.assertEquals(0.2, this.config.getProbability('b'), DELTA);
		Assert.assertEquals(0.3, this.config.getProbability('c'), DELTA);
	}

	@Test
	public void testRemoveLetter() throws Exception {
		this.config.setSupportedLetters(new Letter('a').probability(0.1), new Letter('b').probability(0.2),
				new Letter('c').probability(0.3), new Letter('d').probability(0.4));

		this.config.removeLetters(new Letter('a').probability(0.1));
		Assert.assertArrayEquals(new char[]{'b', 'c', 'd'}, this.config.getSupportedCharacters());
		Assert.assertEquals(0.2, this.config.getProbability('b'), DELTA);
		Assert.assertEquals(0.3, this.config.getProbability('c'), DELTA);
		Assert.assertEquals(0.4, this.config.getProbability('d'), DELTA);

		this.config.removeLetters(new Letter('b').probability(0.2), new Letter('c').probability(0.3));
		Assert.assertArrayEquals(new char[]{'d'}, this.config.getSupportedCharacters());
		Assert.assertEquals(0.4, this.config.getProbability('d'), DELTA);
	}
}
