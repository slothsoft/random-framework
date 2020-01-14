package de.slothsoft.random.types;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomField;
import de.slothsoft.random.types.wordgen.DefaultWordGeneratorConfig;
import de.slothsoft.random.types.wordgen.Letter;

public class WordRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private String word;

		public String getWord() {
			return this.word;
		}

		public void setWord(String value) {
			this.word = value;
		}
	}

	private WordRandomField wordRandomField = new WordRandomField();

	public WordRandomFieldTest() {
		super(new Pojo());
		this.property = "word";
	}

	@Override
	protected RandomField createRandomField() {
		this.wordRandomField = new WordRandomField();
		return this.wordRandomField;
	}

	@Test
	public void testNextWord() throws Exception {
		for (int i = 0; i < 100; i++) {
			final String word = this.wordRandomField.nextValue();

			final String error = "Word #" + (i + 1) + " was not okay.";
			Assert.assertNotNull(error, word);
			Assert.assertFalse(error, word.isEmpty());
		}
	}

	@Test
	public void testSetConfig() throws Exception {
		final DefaultWordGeneratorConfig config1 = new DefaultWordGeneratorConfig().standardWordLength(1);
		this.wordRandomField.setConfig(config1);
		Assert.assertSame(config1, this.wordRandomField.getConfig());

		final DefaultWordGeneratorConfig config2 = new DefaultWordGeneratorConfig().standardWordLength(2);
		this.wordRandomField.config(config2);
		Assert.assertSame(config2, this.wordRandomField.getConfig());
	}

	@Test
	public void testSetConfigDoesStuff() throws Exception {
		this.wordRandomField.setConfig(new DefaultWordGeneratorConfig().letters(new Letter('o')));

		for (int i = 0; i < 100; i++) {
			final String word = this.wordRandomField.nextValue();

			final String error = "Word #" + (i + 1) + " contained invalid letter: " + word;
			Assert.assertNotNull(error, word);
			Assert.assertFalse(error, word.isEmpty());
			for (final char c : word.toCharArray()) {
				Assert.assertEquals(error, 'o', c);
			}
		}
	}

	@Test
	public void testSetConfigWithProbability() throws Exception {
		this.wordRandomField
				.setConfig(new DefaultWordGeneratorConfig().letters(new Letter('o'), new Letter('l').probability(0)));

		for (int i = 0; i < 100; i++) {
			final String word = this.wordRandomField.nextValue();

			final String error = "Word #" + (i + 1) + " contained invalid letter: " + word;
			Assert.assertNotNull(error, word);
			Assert.assertFalse(error, word.isEmpty());
			for (final char c : word.toCharArray()) {
				Assert.assertEquals(error, 'o', c);
			}
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetConfigNoCharacters() throws Exception {
		final DefaultWordGeneratorConfig config = new DefaultWordGeneratorConfig() {
			@Override
			public char[] getSupportedCharacters() {
				return new char[0];
			}
		};
		this.wordRandomField.setConfig(config);
	}
}
