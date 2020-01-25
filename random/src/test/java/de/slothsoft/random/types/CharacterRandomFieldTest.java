package de.slothsoft.random.types;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomField;
import de.slothsoft.random.types.wordgen.DefaultWordGeneratorConfig;
import de.slothsoft.random.types.wordgen.Letter;

public class CharacterRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private Character value;

		public Character getValue() {
			return this.value;
		}

		public void setValue(Character value) {
			this.value = value;
		}
	}

	private CharacterRandomField characterRandomField;

	public CharacterRandomFieldTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		this.characterRandomField = new CharacterRandomField();
		return this.characterRandomField;
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
		this.characterRandomField
				.setConfig(new DefaultWordGeneratorConfig().letters(new Letter('o'), new Letter('l').probability(0)));

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

	@Test
	public void testSetCapitalProbability() throws Throwable {
		this.characterRandomField.setCapitalProbability(0.3);
		Assert.assertEquals(0.3, this.characterRandomField.getCapitalProbability(), DELTA);
	}

	@Test
	public void testCapitalProbability() throws Throwable {
		this.characterRandomField.capitalProbability(0.7);
		Assert.assertEquals(0.7, this.characterRandomField.getCapitalProbability(), DELTA);
	}

	@Test
	public void testSetCapitalProbability0Percent() throws Throwable {
		this.characterRandomField.capitalProbability(0);
		for (int i = 0; i < 100; i++) {
			final Character value = this.characterRandomField.nextValue();
			final String error = "Value #" + i + " was broken!";
			Assert.assertNotNull(error, value);
			Assert.assertEquals(error, String.valueOf(value).toLowerCase(), String.valueOf(value));
		}
	}

	@Test
	public void testSetCapitalProbability100Percent() throws Throwable {
		this.characterRandomField.setCapitalProbability(1);
		for (int i = 0; i < 100; i++) {
			final Character value = this.characterRandomField.nextValue();
			final String error = "Value #" + i + " was broken!";
			Assert.assertNotNull(error, value);
			Assert.assertEquals(error, String.valueOf(value).toUpperCase(), String.valueOf(value));
		}
	}

	@Test
	public void testSetCapitalProbabilityLessThan0Percent() throws Throwable {
		try {
			this.characterRandomField.setCapitalProbability(-0.001);
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertNotNull(e);
		}
	}

	@Test
	public void testSetCapitalProbabilityGreaterThan100Percent() throws Throwable {
		try {
			this.characterRandomField.setCapitalProbability(1.001);
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertNotNull(e);
		}
	}
}
