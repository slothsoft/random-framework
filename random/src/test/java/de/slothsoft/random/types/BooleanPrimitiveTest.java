package de.slothsoft.random.types;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;

public class BooleanPrimitiveTest extends AbstractRandomFieldTest<Boolean> {

	public static class Pojo {

		private boolean value;

		public boolean getValue() {
			return this.value;
		}

		public void setValue(boolean value) {
			this.value = value;
		}

	}

	public BooleanPrimitiveTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField<Boolean> createRandomField() {
		return new BooleanRandomField().primitive(true);
	}

	@Test
	public void testRandomFactoryValue() throws Exception {
		this.randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		this.randomFactory.addRandomField(PROPERTY, this.randomField);

		Assert.assertSame(this.randomField, this.randomFactory.getRandomField(PROPERTY));

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = this.randomFactory.createSingle();
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
		this.randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());

		Assert.assertEquals(null, this.randomFactory.getRandomField(PROPERTY));
	}
}
