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

	private PatternRandomField patternField;

	public PatternRandomFieldTest() {
		super(new Pojo());
	}

	@Override
	protected PatternRandomField createRandomField() {
		this.patternField = new PatternRandomField("");
		return this.patternField;
	}

	@Test
	public void testSetPattern() {
		final String pattern = UUID.randomUUID().toString();
		this.patternField.setPattern(pattern);
		Assert.assertEquals(pattern, this.patternField.getPattern());
	}

	@Test
	public void testPattern() {
		final String pattern = UUID.randomUUID().toString();
		this.patternField.pattern(pattern);
		Assert.assertEquals(pattern, this.patternField.getPattern());
	}

	@Test
	public void testAddComponent() {
		final String key = UUID.randomUUID().toString();
		this.patternField.setPattern(key + ':');

		Assert.assertEquals(key + ':', this.patternField.nextValue());

		this.patternField.addComponent(key, () -> "D");

		Assert.assertEquals("D:", this.patternField.nextValue());
	}

	@Test
	public void testAddComponents() {
		final String key1 = UUID.randomUUID().toString();
		final String key2 = UUID.randomUUID().toString();
		this.patternField.setPattern(key1 + " vs. " + key2);

		Assert.assertEquals(this.patternField.getPattern(), this.patternField.nextValue());

		final Map<String, RandomField> components = new HashMap<>();
		components.put(key1, () -> "A");
		components.put(key2, () -> "B");
		this.patternField.addComponents(components);

		Assert.assertEquals("A vs. B", this.patternField.nextValue());
	}

	@Test
	public void testRemoveComponent() {
		final String key = UUID.randomUUID().toString();
		this.patternField.setPattern(key + ':');

		Assert.assertEquals(key + ':', this.patternField.nextValue());

		this.patternField.addComponent(key, () -> "D");
		this.patternField.removeComponent(key);

		Assert.assertEquals(key + ':', this.patternField.nextValue());
	}

	@Test
	public void testRemoveComponents() {
		final String key1 = UUID.randomUUID().toString();
		final String key2 = UUID.randomUUID().toString();
		this.patternField.setPattern(key1 + " vs. " + key2);

		Assert.assertEquals(this.patternField.getPattern(), this.patternField.nextValue());

		final Map<String, RandomField> components = new HashMap<>();
		components.put(key1, () -> "A");
		components.put(key2, () -> "B");
		this.patternField.addComponents(components);
		this.patternField.removeComponents(components.keySet());

		Assert.assertEquals(this.patternField.getPattern(), this.patternField.nextValue());
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
