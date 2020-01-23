package de.slothsoft.random.types;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;

public class BooleanRandomFieldPrimitiveTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private boolean value;

		public boolean getValue() {
			return this.value;
		}

		public void setValue(boolean value) {
			this.value = value;
		}

	}

	public BooleanRandomFieldPrimitiveTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		return new BooleanRandomField();
	}

	@Test
	public void testRandomFactoryValue() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		randomFactory.addRandomField(property, this.randomField);

		Assert.assertSame(this.randomField, randomFactory.findRandomField(property));

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = randomFactory.createSingle();
			final Object value = getPropertyValue(createdPojo);
			Assert.assertNotNull(value);
		}
	}

	@Test
	public void testValue() throws Exception {
		for (int i = 0; i < 100; i++) {
			final Object value = this.randomField.nextValue();
			Assert.assertNotNull(value);
		}
	}

	@Override
	@Test
	public void testConstructorEmptyMap() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());

		Assert.assertEquals(null, randomFactory.findRandomField(property));
	}
}
