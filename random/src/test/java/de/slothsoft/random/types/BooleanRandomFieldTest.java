package de.slothsoft.random.types;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;

public class BooleanRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private Boolean value;

		public Boolean getValue() {
			return this.value;
		}

		public void setValue(Boolean value) {
			this.value = value;
		}

	}

	private BooleanRandomField field;

	public BooleanRandomFieldTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		this.field = new BooleanRandomField();
		return this.field;
	}

	@Test
	public void testRandomFactoryValue() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		randomFactory.addRandomField(this.property, this.randomField);

		Assert.assertSame(this.randomField, randomFactory.findRandomField(this.property));

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

	@Test
	public void testSetTrueProbability() throws Throwable {
		this.field.setTrueProbability(0.3);
		Assert.assertEquals(0.3, this.field.getTrueProbability(), DELTA);
	}

	@Test
	public void testTrueProbability() throws Throwable {
		this.field.trueProbability(0.7);
		Assert.assertEquals(0.7, this.field.getTrueProbability(), DELTA);
	}

	@Test
	public void testSetTrueProbability0Percent() throws Throwable {
		this.field.trueProbability(0);
		for (int i = 0; i < 100; i++) {
			final Boolean value = this.field.nextValue();
			final String error = "Value #" + i + " was broken!";
			Assert.assertNotNull(error, value);
			Assert.assertFalse(error, value.booleanValue());
		}
	}

	@Test
	public void testSetTrueProbability100Percent() throws Throwable {
		this.field.setTrueProbability(1);
		for (int i = 0; i < 100; i++) {
			final Boolean value = this.field.nextValue();
			final String error = "Value #" + i + " was broken!";
			Assert.assertNotNull(error, value);
			Assert.assertTrue(error, value.booleanValue());
		}
	}

	@Test
	public void testSetTrueProbabilityLessThan0Percent() throws Throwable {
		try {
			this.field.setTrueProbability(-0.001);
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertNotNull(e);
		}
	}

	@Test
	public void testSetTrueProbabilityGreaterThan100Percent() throws Throwable {
		try {
			this.field.setTrueProbability(1.001);
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertNotNull(e);
		}
	}
}
