package de.slothsoft.random.types;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;

public class BooleanTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private Boolean value;

		public Boolean getValue() {
			return this.value;
		}

		public void setValue(Boolean value) {
			this.value = value;
		}

	}

	public BooleanTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		return new BooleanRandomField();
	}

	@Test
	public void testRandomFactoryValue() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		randomFactory.addRandomField(PROPERTY, this.randomField);

		Assert.assertSame(this.randomField, randomFactory.getRandomField(PROPERTY));

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

}
