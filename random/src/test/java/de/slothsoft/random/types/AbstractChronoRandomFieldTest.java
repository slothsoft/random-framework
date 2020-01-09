package de.slothsoft.random.types;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.slothsoft.random.RandomFactory;

@SuppressWarnings("unchecked")
public abstract class AbstractChronoRandomFieldTest<C> extends AbstractRandomFieldTest {

	protected AbstractChronoRandomField<C> chronoField;

	private final C lowValue;
	private final C highValue;

	public AbstractChronoRandomFieldTest(Object pojo, C lowValue, C highValue) {
		super(pojo);
		this.lowValue = lowValue;
		this.highValue = highValue;
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.chronoField = createRandomField();
	}

	@Override
	protected abstract AbstractChronoRandomField<C> createRandomField();

	@Override
	@Test
	public void testConstructorEmptyMap() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());

		Assert.assertEquals(null, randomFactory.getRandomField(property));
	}

	@Test
	public void testIsBefore() throws Exception {
		Assert.assertTrue(this.lowValue + " < " + this.highValue, this.chronoField.isBefore(this.lowValue, this.highValue));
		Assert.assertFalse(this.highValue + " < " + this.lowValue, this.chronoField.isBefore(this.highValue, this.lowValue));
	}

	@Test
	public void testIsAfter() throws Exception {
		Assert.assertFalse(this.lowValue + " > " + this.highValue, this.chronoField.isAfter(this.lowValue, this.highValue));
		Assert.assertTrue(this.highValue + " > " + this.lowValue, this.chronoField.isAfter(this.highValue, this.lowValue));
	}

	@Test
	public void testIsEqual() throws Exception {
		Assert.assertFalse(this.lowValue + " = " + this.highValue, this.chronoField.isEqual(this.lowValue, this.highValue));
		Assert.assertFalse(this.lowValue + " = " + this.highValue, this.chronoField.isEqual(this.highValue, this.lowValue));

		Assert.assertTrue(this.highValue + " = " + this.highValue, this.chronoField.isEqual(this.highValue, this.highValue));
		Assert.assertTrue(this.lowValue + " = " + this.lowValue, this.chronoField.isEqual(this.lowValue, this.lowValue));
	}

	@Test
	public void testHighValueBeforeLowValue() throws Exception {
		Assert.assertTrue(this.highValue + " > " + this.lowValue, this.chronoField.isAfter(this.highValue, this.lowValue));
		Assert.assertFalse(this.lowValue + " > " + this.highValue, this.chronoField.isAfter(this.lowValue, this.highValue));
	}

	@Test
	public void testRandomFactoryStartValue() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		randomFactory.addRandomField(property, this.chronoField);

		Assert.assertSame(this.chronoField, randomFactory.getRandomField(property));

		this.chronoField.setStartValue(this.highValue);

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = randomFactory.createSingle();
			final C value = (C) getPropertyValue(createdPojo);
			Assert.assertNotNull(value);
			Assert.assertFalse("Value should be after start value: " + value + " > " + this.highValue,
					this.chronoField.isBefore(value, this.highValue));
		}
	}

	@Test
	public void testStartValue() throws Exception {
		this.chronoField.setStartValue(this.highValue);

		for (int i = 0; i < 100; i++) {
			final C value = this.chronoField.nextValue();
			Assert.assertNotNull(value);
			Assert.assertFalse("Value should be after start value: " + value + " > " + this.highValue,
					this.chronoField.isBefore(value, this.highValue));
		}
	}

	@Test
	public void testRandomFactoryEndValue() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		randomFactory.addRandomField(property, this.chronoField);

		Assert.assertSame(this.chronoField, randomFactory.getRandomField(property));

		this.chronoField.setEndValue(this.highValue);

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = randomFactory.createSingle();
			final C value = (C) getPropertyValue(createdPojo);
			Assert.assertNotNull(value);
			Assert.assertFalse("Value should be before end value: " + value + " < " + this.highValue,
					this.chronoField.isAfter(value, this.highValue));
		}
	}

	@Test
	public void testEndValue() throws Exception {
		this.chronoField.setEndValue(this.highValue);

		for (int i = 0; i < 100; i++) {
			final C value = this.chronoField.nextValue();
			Assert.assertNotNull(value);
			Assert.assertFalse("Value should be before end value: " + value + " < " + this.highValue,
					this.chronoField.isAfter(value, this.highValue));
		}
	}

	@Test
	public void testRandomFactoryStartValueAndEndValue() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		randomFactory.addRandomField(property, this.chronoField);

		Assert.assertSame(this.chronoField, randomFactory.getRandomField(property));

		this.chronoField.setStartValue(this.lowValue);
		this.chronoField.setEndValue(this.highValue);

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = randomFactory.createSingle();
			final C value = (C) getPropertyValue(createdPojo);
			Assert.assertFalse("Value should be after start value: " + value + " > " + this.lowValue,
					this.chronoField.isBefore(value, this.lowValue));
			Assert.assertFalse("Value should be before end value: " + value + " < " + this.highValue,
					this.chronoField.isAfter(value, this.highValue));
		}
	}

	@Test
	public void testStartValueAndEndValue() throws Exception {
		this.chronoField.setStartValue(this.lowValue);
		this.chronoField.setEndValue(this.highValue);

		for (int i = 0; i < 100; i++) {
			final C value = this.chronoField.nextValue();
			Assert.assertNotNull(value);
			Assert.assertFalse("Value should be after start value: " + value + " > " + this.lowValue,
					this.chronoField.isBefore(value, this.lowValue));
			Assert.assertFalse("Value should be before end value: " + value + " < " + this.highValue,
					this.chronoField.isAfter(value, this.highValue));
		}
	}

	@Test
	public void testRandomFactoryStartValueAndEndValueSwitched() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		randomFactory.addRandomField(property, this.chronoField);

		Assert.assertSame(this.chronoField, randomFactory.getRandomField(property));

		this.chronoField.setStartValue(this.highValue);
		this.chronoField.setEndValue(this.lowValue);

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = randomFactory.createSingle();
			final C value = (C) getPropertyValue(createdPojo);
			Assert.assertFalse("Value should be after start value: " + value + " > " + this.lowValue,
					this.chronoField.isBefore(value, this.lowValue));
			Assert.assertFalse("Value should be before end value: " + value + " < " + this.highValue,
					this.chronoField.isAfter(value, this.highValue));
		}
	}

	@Test
	public void testStartValueAndEndValueSwitched() throws Exception {
		this.chronoField.setStartValue(this.highValue);
		this.chronoField.setEndValue(this.lowValue);

		for (int i = 0; i < 100; i++) {
			final C value = this.chronoField.nextValue();
			Assert.assertNotNull(value);
			Assert.assertFalse("Value should be after start value: " + value + " > " + this.lowValue,
					this.chronoField.isBefore(value, this.lowValue));
			Assert.assertFalse("Value should be before end value: " + value + " < " + this.highValue,
					this.chronoField.isAfter(value, this.highValue));
		}
	}
	@Test
	public void testRandomFactoryStartValueEqualsEndValue() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		randomFactory.addRandomField(property, this.chronoField);

		Assert.assertSame(this.chronoField, randomFactory.getRandomField(property));

		this.chronoField.setStartValue(this.highValue);
		this.chronoField.setEndValue(this.highValue);

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = randomFactory.createSingle();
			final C value = (C) getPropertyValue(createdPojo);
			Assert.assertEquals(value, this.highValue);
		}
	}

	@Test
	public void testStartValueEqualsEndValue() throws Exception {
		this.chronoField.setStartValue(this.lowValue);
		this.chronoField.setEndValue(this.lowValue);

		for (int i = 0; i < 100; i++) {
			final C value = this.chronoField.nextValue();
			Assert.assertNotNull(value);
			Assert.assertEquals(value, this.lowValue);
		}
	}

	@Test
	public void testToAndFromLong() throws Exception {
		final long value = this.chronoField.toLongValue(this.lowValue);
		final C copyOfLowValue = this.chronoField.fromLongValue(value);

		Assert.assertEquals(copyOfLowValue, this.lowValue);
	}

	@Test
	public void testFromAndToLong() throws Exception {
		final long initialLong = this.chronoField.toLongValue(this.highValue);
		final C value = this.chronoField.fromLongValue(initialLong);
		final long copyOfLong = this.chronoField.toLongValue(value);

		Assert.assertEquals(initialLong, copyOfLong);
	}

	@Test
	public void testSetEndValue() throws Exception {
		this.chronoField.setEndValue(this.highValue);
		Assert.assertEquals(this.highValue, this.chronoField.getEndValue());

		this.chronoField.endValue(this.lowValue);
		Assert.assertEquals(this.lowValue, this.chronoField.getEndValue());
	}

	@Test
	public void testSetStartValue() throws Exception {
		this.chronoField.setStartValue(this.highValue);
		Assert.assertEquals(this.highValue, this.chronoField.getStartValue());

		this.chronoField.startValue(this.lowValue);
		Assert.assertEquals(this.lowValue, this.chronoField.getStartValue());
	}
}
