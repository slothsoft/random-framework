package de.slothsoft.random.types;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomField;
import de.slothsoft.random.types.wordgen.DefaultWordGeneratorConfig;

public class WordsRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private String comment;

		public String getComment() {
			return this.comment;
		}

		public void setComment(String value) {
			this.comment = value;
		}
	}

	private WordsRandomField wordsRandomField;

	public WordsRandomFieldTest() {
		super(new Pojo());
		this.property = "comment";
	}

	@Override
	protected RandomField createRandomField() {
		this.wordsRandomField = new WordsRandomField();
		return this.wordsRandomField;
	}

	@Test
	public void testNextWords() throws Exception {
		for (int i = 0; i < 100; i++) {
			final String words = this.wordsRandomField.nextValue();

			final String error = "Word #" + (i + 1) + " was not okay. ";
			Assert.assertNotNull(error, words);
			Assert.assertFalse(error, words.isEmpty());
			Assert.assertTrue(error + "It should start with upper case: " + words,
					Character.isUpperCase(words.charAt(0)));
			Assert.assertEquals(error + "It should end with dot: " + words, '.', words.charAt(words.length() - 1));
		}
	}

	@Test
	public void testSetConfig() throws Exception {
		final DefaultWordGeneratorConfig config1 = new DefaultWordGeneratorConfig().standardWordLength(1);
		this.wordsRandomField.setConfig(config1);
		Assert.assertSame(config1, this.wordsRandomField.getConfig());

		final DefaultWordGeneratorConfig config2 = new DefaultWordGeneratorConfig().standardWordLength(2);
		this.wordsRandomField.config(config2);
		Assert.assertSame(config2, this.wordsRandomField.getConfig());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetConfigNoCharacters() throws Exception {
		final DefaultWordGeneratorConfig config = new DefaultWordGeneratorConfig() {
			@Override
			public char[] getSupportedCharacters() {
				return new char[0];
			}
		};
		this.wordsRandomField.setConfig(config);
	}

	@Test
	public void testSetParagraphCount() throws Exception {
		this.wordsRandomField.setParagraphCount(2);
		Assert.assertEquals(2, this.wordsRandomField.getParagraphCount());

		this.wordsRandomField.paragraphCount(3);
		Assert.assertEquals(3, this.wordsRandomField.getParagraphCount());
	}
}
