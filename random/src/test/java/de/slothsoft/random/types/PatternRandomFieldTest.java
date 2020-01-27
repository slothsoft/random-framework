package de.slothsoft.random.types;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomField;

public class PatternRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private String value;

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	private PatternRandomField patternRandomField;

	public PatternRandomFieldTest() {
		super(new Pojo());
	}

	@Override
	protected PatternRandomField createRandomField() {
		this.patternRandomField = new PatternRandomField("");
		return this.patternRandomField;
	}

	@Test
	public void testSetPattern() {
		final String pattern = UUID.randomUUID().toString();
		this.patternRandomField.setPattern(pattern);
		Assert.assertEquals(pattern, this.patternRandomField.getPattern());
	}

	@Test
	public void testPattern() {
		final String pattern = UUID.randomUUID().toString();
		this.patternRandomField.pattern(pattern);
		Assert.assertEquals(pattern, this.patternRandomField.getPattern());
	}

	@Test
	public void testComponentNextValueIsNull() {
		final String key = UUID.randomUUID().toString();
		this.patternRandomField.addComponent(key, () -> null);

		Assert.assertEquals("", this.patternRandomField.pattern(key).nextValue());
		Assert.assertEquals("!!", this.patternRandomField.pattern("!" + key + "!").nextValue());
	}

	@Test
	public void testAddComponent() {
		final String key = UUID.randomUUID().toString();
		this.patternRandomField.setPattern(key + ':');

		Assert.assertEquals(key + ':', this.patternRandomField.nextValue());

		this.patternRandomField.addComponent(key, () -> "D");

		Assert.assertEquals("D:", this.patternRandomField.nextValue());
	}

	@Test
	public void testAddComponents() {
		final String key1 = UUID.randomUUID().toString();
		final String key2 = UUID.randomUUID().toString();
		this.patternRandomField.setPattern(key1 + " vs. " + key2);

		Assert.assertEquals(this.patternRandomField.getPattern(), this.patternRandomField.nextValue());

		final Map<String, RandomField> components = new HashMap<>();
		components.put(key1, () -> "A");
		components.put(key2, () -> "B");
		this.patternRandomField.addComponents(components);

		Assert.assertEquals("A vs. B", this.patternRandomField.nextValue());
	}

	@Test
	public void testRemoveComponent() {
		final String key = UUID.randomUUID().toString();
		this.patternRandomField.setPattern(key + ':');

		Assert.assertEquals(key + ':', this.patternRandomField.nextValue());

		this.patternRandomField.addComponent(key, () -> "D");
		this.patternRandomField.removeComponent(key);

		Assert.assertEquals(key + ':', this.patternRandomField.nextValue());
	}

	@Test
	public void testRemoveComponents() {
		final String key1 = UUID.randomUUID().toString();
		final String key2 = UUID.randomUUID().toString();
		this.patternRandomField.setPattern(key1 + " vs. " + key2);

		Assert.assertEquals(this.patternRandomField.getPattern(), this.patternRandomField.nextValue());

		final Map<String, RandomField> components = new HashMap<>();
		components.put(key1, () -> "A");
		components.put(key2, () -> "B");
		this.patternRandomField.addComponents(components);
		this.patternRandomField.removeComponents(components.keySet());

		Assert.assertEquals(this.patternRandomField.getPattern(), this.patternRandomField.nextValue());
	}

	@Override
	@Test
	public void testForClassGuess() throws Exception {
		// is not guessed on it's own
	}

	@Override
	@Test
	public void testConstructorGuess() throws Exception {
		// is not guessed on it's own
	}

}
