package de.slothsoft.random.types;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;

public abstract class AbstractRandomFieldTest {

	static final String PROPERTY_NULL_PROBABILITY = "nullProbability";
	static final String SETTER_NULL_PROBABILITY = "set" + PROPERTY_NULL_PROBABILITY.substring(0, 1).toUpperCase()
			+ PROPERTY_NULL_PROBABILITY.substring(1);
	static final String GETTER_NULL_PROBABILITY = "get" + PROPERTY_NULL_PROBABILITY.substring(0, 1).toUpperCase()
			+ PROPERTY_NULL_PROBABILITY.substring(1);

	static final double DELTA = 0.0001;

	protected String property = "value";

	protected final Object pojo;
	protected RandomField randomField;

	public AbstractRandomFieldTest(Object pojo) {
		this.pojo = pojo;
	}

	@Before
	public void setUp() throws Exception {
		this.randomField = createRandomField();
	}

	protected abstract RandomField createRandomField();

	@Test
	public void testForClassGuess() throws Exception {
		final RandomFactory<?> randomFactory = RandomFactory.forClass(this.pojo.getClass());

		final Object createdPojo = randomFactory.createSingle();
		Assert.assertNotNull(getPropertyValue(createdPojo));
	}

	Object getPropertyValue(Object object) throws Exception {
		final Field field = object.getClass().getDeclaredField(this.property);
		field.setAccessible(true);
		return field.get(object);
	}

	@Test
	public void testConstructorGuess() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo);

		final Object createdPojo = randomFactory.createSingle();
		Assert.assertNotNull(getPropertyValue(createdPojo));
	}

	@Test
	public void testConstructorEmptyMap() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());

		Assert.assertEquals(null, randomFactory.findRandomField(this.property));

		final Object createdPojo = randomFactory.createSingle();
		Assert.assertNull(getPropertyValue(createdPojo));
	}

	@Test
	public void testConstructorManual() throws Exception {
		final RandomFactory<?> randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		randomFactory.addRandomField(this.property, this.randomField);

		Assert.assertSame(this.randomField, randomFactory.findRandomField(this.property));

		final Object createdPojo = randomFactory.createSingle();
		Assert.assertNotNull(getPropertyValue(createdPojo));
	}

	@Test
	public void testSetNullProbability() throws Throwable {
		if (!isNullProbabilitySupported()) {
			return;
		}

		invokeSetterMethod(this.randomField, SETTER_NULL_PROBABILITY, double.class, Double.valueOf(0.3));
		Assert.assertEquals(0.3, ((Double) invokeGetterMethod(this.randomField, GETTER_NULL_PROBABILITY)).doubleValue(),
				DELTA);
	}

	protected boolean isNullProbabilitySupported() {
		return true;
	}

	private static void invokeSetterMethod(Object object, String setterName, Class<?> valueClass, Object value)
			throws Throwable {
		Class<?> checkedClass = object.getClass();
		while (checkedClass != null) {
			try {
				final Method method = checkedClass.getMethod(setterName, valueClass);
				method.invoke(object, value);
				return;
			} catch (IllegalAccessException | NoSuchMethodException | SecurityException e) {
				// ignore
			} catch (final InvocationTargetException e) {
				throw e.getTargetException();
			}
			checkedClass = checkedClass.getSuperclass();
		}
		Assert.fail("Could not find method " + setterName + " on " + object);
	}

	private static Object invokeGetterMethod(Object object, String getterName) throws Throwable {
		Class<?> checkedClass = object.getClass();
		while (checkedClass != null) {
			try {
				final Method method = checkedClass.getMethod(getterName);
				return method.invoke(object);
			} catch (IllegalAccessException | NoSuchMethodException | SecurityException e) {
				// ignore
			} catch (final InvocationTargetException e) {
				throw e.getTargetException();
			}
			checkedClass = checkedClass.getSuperclass();
		}
		Assert.fail("Could not find method " + getterName + " on " + object);
		return null;
	}

	@Test
	public void testNullProbability() throws Throwable {
		if (!isNullProbabilitySupported()) {
			return;
		}

		invokeSetterMethod(this.randomField, PROPERTY_NULL_PROBABILITY, double.class, Double.valueOf(0.7));
		Assert.assertEquals(0.7, ((Double) invokeGetterMethod(this.randomField, GETTER_NULL_PROBABILITY)).doubleValue(),
				DELTA);
	}

	@Test
	public void testSetNullProbability0Percent() throws Throwable {
		if (!isNullProbabilitySupported()) {
			return;
		}

		invokeSetterMethod(this.randomField, PROPERTY_NULL_PROBABILITY, double.class, Double.valueOf(0));
		for (int i = 0; i < 100; i++) {
			Assert.assertNotNull(this.randomField.nextValue());
		}
	}

	@Test
	public void testSetNullProbability100Percent() throws Throwable {
		if (!isNullProbabilitySupported()) {
			return;
		}

		invokeSetterMethod(this.randomField, SETTER_NULL_PROBABILITY, double.class, Double.valueOf(1));
		for (int i = 0; i < 100; i++) {
			Assert.assertNull(this.randomField.nextValue());
		}
	}

	@Test
	public void testSetNullProbabilityLessThan0Percent() throws Throwable {
		if (!isNullProbabilitySupported()) {
			return;
		}
		try {
			invokeSetterMethod(this.randomField, SETTER_NULL_PROBABILITY, double.class, Double.valueOf(-0.001));
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertNotNull(e);
		}
	}

	@Test
	public void testSetNullProbabilityGreaterThan100Percent() throws Throwable {
		if (!isNullProbabilitySupported()) {
			return;
		}
		try {
			invokeSetterMethod(this.randomField, SETTER_NULL_PROBABILITY, double.class, Double.valueOf(1.001));
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertNotNull(e);
		}
	}
}
