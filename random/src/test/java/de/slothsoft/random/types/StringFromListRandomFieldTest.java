package de.slothsoft.random.types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;

public class StringFromListRandomFieldTest extends AbstractRandomFieldTest {

	private static final String[] VALUES = {"A", "B", "C"};
	public static class Pojo {

		private String value;

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	public StringFromListRandomFieldTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		return new ElementFromListRandomField(VALUES);
	}

	@Test
	public void testRandomFactoryValue() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		randomFactory.addRandomField(PROPERTY, this.randomField);

		Assert.assertSame(this.randomField, randomFactory.getRandomField(PROPERTY));

		final List<String> allValues = Arrays.asList(VALUES);

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = randomFactory.createSingle();
			final Object value = getPropertyValue(createdPojo);
			Assert.assertNotNull(value);
			Assert.assertTrue("Invalid value: " + value, allValues.contains(value));
		}
	}

	@Test
	public void testValue() throws Exception {
		final List<String> allValues = Arrays.asList(VALUES);

		for (int i = 0; i < 100; i++) {
			final Object value = this.randomField.nextValue();
			Assert.assertNotNull(value);
			Assert.assertTrue("Invalid value: " + value, allValues.contains(value));
		}
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
