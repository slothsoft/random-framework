package de.slothsoft.random.types;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.slothsoft.random.RandomFactory;

public abstract class AbstractNumberRandomFieldTest<N extends Number> extends AbstractRandomFieldTest<N> {

	protected static final String PROPERTY = "value";

	protected AbstractNumberRandomField<N> randomField;

	private final N seven;
	private final N ninetyTwo;

	public AbstractNumberRandomFieldTest(Object pojo, N seven, N ninetyTwo) {
		super(pojo);
		this.seven = seven;
		this.ninetyTwo = ninetyTwo;
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.randomField = createRandomField();
	}

	@Override
	protected abstract AbstractNumberRandomField<N> createRandomField();

	@Override
	@Test
	public void testConstructorEmptyMap() throws Exception {
		this.randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());

		Assert.assertEquals(null, this.randomFactory.getRandomField(PROPERTY));

		if (this.randomField instanceof AbstractPrimitiveNumberRandomField<?>
				&& !((AbstractPrimitiveNumberRandomField<?>) this.randomField).isPrimitive()) {
			final Object createdPojo = this.randomFactory.createSingle();
			Assert.assertNull(getPropertyValue(createdPojo));
		}
	}

	@Test
	public void testRandomFactoryStartValue() throws Exception {
		this.randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		this.randomFactory.addRandomField(PROPERTY, this.randomField);

		Assert.assertSame(this.randomField, this.randomFactory.getRandomField(PROPERTY));

		this.randomField.setStartValue(this.ninetyTwo);

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = this.randomFactory.createSingle();
			final Number value = (Number) getPropertyValue(createdPojo);
			Assert.assertNotNull(value);
			Assert.assertTrue("Value is to small: " + value, value.doubleValue() >= this.ninetyTwo.doubleValue());
		}
	}

	@Test
	public void testStartValue() throws Exception {
		this.randomField.setStartValue(this.ninetyTwo);

		for (int i = 0; i < 100; i++) {
			final N value = this.randomField.nextValue();
			Assert.assertNotNull(value);
			Assert.assertTrue("Value is to small: " + value, value.doubleValue() >= this.ninetyTwo.doubleValue());
		}
	}

	@Test
	public void testRandomFactoryEndValue() throws Exception {
		this.randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		this.randomFactory.addRandomField(PROPERTY, this.randomField);

		Assert.assertSame(this.randomField, this.randomFactory.getRandomField(PROPERTY));

		this.randomField.setEndValue(this.ninetyTwo);

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = this.randomFactory.createSingle();
			final Number value = (Number) getPropertyValue(createdPojo);
			Assert.assertNotNull(value);
			Assert.assertTrue("Value is to big: " + value, value.doubleValue() < this.ninetyTwo.doubleValue());
		}
	}

	@Test
	public void testEndValue() throws Exception {
		this.randomField.setEndValue(this.ninetyTwo);

		for (int i = 0; i < 100; i++) {
			final N value = this.randomField.nextValue();
			Assert.assertNotNull(value);
			Assert.assertTrue("Value is to big: " + value, value.doubleValue() < this.ninetyTwo.doubleValue());
		}
	}

	@Test
	public void testRandomFactoryStartValueAndEndValue() throws Exception {
		this.randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		this.randomFactory.addRandomField(PROPERTY, this.randomField);

		Assert.assertSame(this.randomField, this.randomFactory.getRandomField(PROPERTY));

		this.randomField.setStartValue(this.seven);
		this.randomField.setEndValue(this.ninetyTwo);

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = this.randomFactory.createSingle();
			final Number value = (Number) getPropertyValue(createdPojo);
			Assert.assertTrue("Value is to small: " + value, value.doubleValue() >= this.seven.doubleValue());
			Assert.assertTrue("Value is to big: " + value, value.doubleValue() < this.ninetyTwo.doubleValue());
		}
	}

	@Test
	public void testStartValueAndEndValue() throws Exception {
		this.randomField.setStartValue(this.seven);
		this.randomField.setEndValue(this.ninetyTwo);

		for (int i = 0; i < 100; i++) {
			final N value = this.randomField.nextValue();
			Assert.assertNotNull(value);
			Assert.assertTrue("Value is to small: " + value, value.doubleValue() >= this.seven.doubleValue());
			Assert.assertTrue("Value is to big: " + value, value.doubleValue() < this.ninetyTwo.doubleValue());
		}
	}

	@Test
	public void testRandomFactoryStartValueAndEndValueSwitched() throws Exception {
		this.randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		this.randomFactory.addRandomField(PROPERTY, this.randomField);

		Assert.assertSame(this.randomField, this.randomFactory.getRandomField(PROPERTY));

		this.randomField.setStartValue(this.ninetyTwo);
		this.randomField.setEndValue(this.seven);

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = this.randomFactory.createSingle();
			final Number value = (Number) getPropertyValue(createdPojo);
			Assert.assertTrue("Value is to small: " + value, value.doubleValue() >= this.seven.doubleValue());
			Assert.assertTrue("Value is to big: " + value, value.doubleValue() < this.ninetyTwo.doubleValue());
		}
	}

	@Test
	public void testStartValueAndEndValueSwitched() throws Exception {
		this.randomField.setStartValue(this.ninetyTwo);
		this.randomField.setEndValue(this.seven);

		for (int i = 0; i < 100; i++) {
			final N value = this.randomField.nextValue();
			Assert.assertNotNull(value);
			Assert.assertTrue("Value is to small: " + value, value.doubleValue() >= this.seven.doubleValue());
			Assert.assertTrue("Value is to big: " + value, value.doubleValue() < this.ninetyTwo.doubleValue());
		}
	}
	@Test
	public void testRandomFactoryStartValueEqualsEndValue() throws Exception {
		this.randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		this.randomFactory.addRandomField(PROPERTY, this.randomField);

		Assert.assertSame(this.randomField, this.randomFactory.getRandomField(PROPERTY));

		this.randomField.setStartValue(this.ninetyTwo);
		this.randomField.setEndValue(this.ninetyTwo);

		for (int i = 0; i < 100; i++) {
			final Object createdPojo = this.randomFactory.createSingle();
			final Number value = (Number) getPropertyValue(createdPojo);
			Assert.assertEquals(value.doubleValue(), this.ninetyTwo.doubleValue(), 0.001);
		}
	}

	@Test
	public void testStartValueEqualsEndValue() throws Exception {
		this.randomField.setStartValue(this.seven);
		this.randomField.setEndValue(this.seven);

		for (int i = 0; i < 100; i++) {
			final N value = this.randomField.nextValue();
			Assert.assertNotNull(value);
			Assert.assertEquals(value.doubleValue(), this.seven.doubleValue(), 0.001);
		}
	}

}
