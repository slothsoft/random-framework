package de.slothsoft.random.types;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.slothsoft.random.RandomField;

public class PatternRandomFieldTest {

	private PatternRandomField randomField;

	@Before
	public void setUp() throws Exception {
		this.randomField = createPatternRandomField();
	}

	protected PatternRandomField createPatternRandomField() {
		return new PatternRandomField("");
	}

	@Test
	public void testSetPattern() {
		final String pattern = UUID.randomUUID().toString();
		this.randomField.setPattern(pattern);
		Assert.assertEquals(pattern, this.randomField.getPattern());
	}

	@Test
	public void testPattern() {
		final String pattern = UUID.randomUUID().toString();
		this.randomField.pattern(pattern);
		Assert.assertEquals(pattern, this.randomField.getPattern());
	}

	@Test
	public void testAddComponent() {
		final String key = UUID.randomUUID().toString();
		this.randomField.setPattern(key + ':');

		Assert.assertEquals(key + ':', this.randomField.nextValue());

		this.randomField.addComponent(key, () -> "D");

		Assert.assertEquals("D:", this.randomField.nextValue());
	}

	@Test
	public void testAddComponents() {
		final String key1 = UUID.randomUUID().toString();
		final String key2 = UUID.randomUUID().toString();
		this.randomField.setPattern(key1 + " vs. " + key2);

		Assert.assertEquals(this.randomField.getPattern(), this.randomField.nextValue());

		final Map<String, RandomField> components = new HashMap<>();
		components.put(key1, () -> "A");
		components.put(key2, () -> "B");
		this.randomField.addComponents(components);

		Assert.assertEquals("A vs. B", this.randomField.nextValue());
	}

	@Test
	public void testRemoveComponent() {
		final String key = UUID.randomUUID().toString();
		this.randomField.setPattern(key + ':');

		Assert.assertEquals(key + ':', this.randomField.nextValue());

		this.randomField.addComponent(key, () -> "D");
		this.randomField.removeComponent(key);

		Assert.assertEquals(key + ':', this.randomField.nextValue());
	}

	@Test
	public void testRemoveComponents() {
		final String key1 = UUID.randomUUID().toString();
		final String key2 = UUID.randomUUID().toString();
		this.randomField.setPattern(key1 + " vs. " + key2);

		Assert.assertEquals(this.randomField.getPattern(), this.randomField.nextValue());

		final Map<String, RandomField> components = new HashMap<>();
		components.put(key1, () -> "A");
		components.put(key2, () -> "B");
		this.randomField.addComponents(components);
		this.randomField.removeComponents(components.keySet());

		Assert.assertEquals(this.randomField.getPattern(), this.randomField.nextValue());
	}

}
