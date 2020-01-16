package de.slothsoft.random.types;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;
import de.slothsoft.random.types.wordgen.DefaultWordGeneratorConfig;
import de.slothsoft.random.types.wordgen.Letter;

public class CharacterRandomFieldPrimitiveTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private char value;

		public char getValue() {
			return this.value;
		}

		public void setValue(char value) {
			this.value = value;
		}
	}

	private CharacterRandomField characterRandomField;

	public CharacterRandomFieldPrimitiveTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		this.characterRandomField = new CharacterRandomField();
		return this.characterRandomField;
	}

	@Override
	@Test
	public void testConstructorEmptyMap() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());

		Assert.assertEquals(null, randomFactory.getRandomField(this.property));
	}

	@Test
	public void testNextWord() throws Exception {
		for (int i = 0; i < 100; i++) {
			final Character letter = this.characterRandomField.nextValue();

			final String error = "Letter #" + (i + 1) + " was not okay.";
			Assert.assertNotNull(error, letter);
		}
	}

	@Test
	public void testSetConfig() throws Exception {
		final DefaultWordGeneratorConfig config1 = new DefaultWordGeneratorConfig().standardWordLength(1);
		this.characterRandomField.setConfig(config1);
		Assert.assertSame(config1, this.characterRandomField.getConfig());

		final DefaultWordGeneratorConfig config2 = new DefaultWordGeneratorConfig().standardWordLength(2);
		this.characterRandomField.config(config2);
		Assert.assertSame(config2, this.characterRandomField.getConfig());
	}

	@Test
	public void testSetConfigDoesStuff() throws Exception {
		this.characterRandomField.setConfig(new DefaultWordGeneratorConfig().letters(new Letter('o')));

		for (int i = 0; i < 100; i++) {
			final Character letter = this.characterRandomField.nextValue();

			final String error = "Letter #" + (i + 1) + " was invalid letter: " + letter;
			Assert.assertNotNull(error, letter);
			Assert.assertEquals(error, 'o', letter.charValue());
		}
	}

	@Test
	public void testSetConfigWithProbability() throws Exception {
		this.characterRandomField.setConfig(new DefaultWordGeneratorConfig().letters(new Letter('o'), new Letter('l').probability(0)));

		for (int i = 0; i < 100; i++) {
			final Character letter = this.characterRandomField.nextValue();

			final String error = "Letter #" + (i + 1) + " was invalid letter: " + letter;
			Assert.assertNotNull(error, letter);
			Assert.assertEquals(error, 'o', letter.charValue());
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
		this.characterRandomField.setConfig(config);
	}
}
