package de.slothsoft.random;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/*
 * This class test the default behavior, e.g. fill fields with special names or types.
 */

@RunWith(Parameterized.class)
public class RandomFactoryTest {

	private static int classCounter;

	@Parameters(name = "{1} ({0})")
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(new Object[][]{

				data("bigDecimal", BigDecimal.class),

				data("bigInt", BigInteger.class),

				data("bool", Boolean.class),

				data("smallBool", boolean.class),

				data("calendar", Calendar.class),

				data("character", Character.class),

				data("smallCharacter", char.class),

				data("birthday", Date.class),

				data("city", String.class),

				data("someDate", Date.class),

				data("doubleTrouble", Double.class),

				data("smallTrouble", double.class),

				data("value", DayOfWeek.class),

				data("firstName", String.class),

				data("floating", Float.class),

				data("smallFloating", float.class),

				data("integer", Integer.class),

				data("smallInt", int.class),

				data("lastName", String.class),

				data("someDate", LocalDate.class),

				data("someDateTime", LocalDateTime.class),

				data("someTime", LocalTime.class),

				data("longJohns", Long.class),

				data("smallJohns", long.class),

				data("shorty", Short.class),

				data("smallShorty", short.class),

				data("street", String.class),

				data("word", String.class),

				data("comments", String.class),

		});
	}

	private static Object[] data(String propertyName, Class<?> propertyClass) throws Exception {
		return new Object[]{PojoGenerator.generate("Pojo" + classCounter++, propertyName, propertyClass), propertyName};
	}

	private final Class<?> pojoClass;
	private final String propertyName;

	public RandomFactoryTest(Class<?> pojoClass, String propertyName) {
		this.pojoClass = pojoClass;
		this.propertyName = propertyName;
	}

	@Test
	public void testForPojoCreateSingle() throws Exception {
		final RandomFactory<?> factory = RandomFactory.forClass(this.pojoClass);

		final Object pojo = factory.createSingle();
		final Object propertyValue = getPropertyValue(pojo);

		Assert.assertNotNull("Property " + this.propertyName + " was not filled correctly for " + this.pojoClass,
				propertyValue);

		if (propertyValue instanceof Number) {
			Assert.assertNotEquals("Value should be over zero on default: " + propertyValue,
					((Number) propertyValue).doubleValue(), 0.0, 0.0);
		}
	}

	private Object getPropertyValue(Object pojo) throws Exception {
		final Field field = pojo.getClass().getDeclaredField(this.propertyName);
		field.setAccessible(true);
		return field.get(pojo);
	}

	@Test
	public void testForPojoCreate() throws Exception {
		final RandomFactory<?> factory = RandomFactory.forClass(this.pojoClass);

		for (final Object pojo : factory.create(5)) {
			final Object propertyValue = getPropertyValue(pojo);

			Assert.assertNotNull("Property " + this.propertyName + " was not filled correctly for " + this.pojoClass,
					propertyValue);

			if (propertyValue instanceof Number) {
				Assert.assertNotEquals("Value should be over zero on default: " + propertyValue,
						((Number) propertyValue).doubleValue(), 0.0, 0.0);
			}
		}
	}

	@Test
	public void testConstructorEmptyHashMap() throws Exception {
		if (this.propertyName.startsWith("small")) return; // it's a primitive property

		final RandomFactory<?> factory = new RandomFactory<>(this::createPojo, new HashMap<>());

		final Object pojo = factory.createSingle();
		final Object propertyValue = getPropertyValue(pojo);

		Assert.assertNull("Property " + this.propertyName + " was filled for " + this.pojoClass, propertyValue);
	}

	private Object createPojo() {
		try {
			return this.pojoClass.getConstructor().newInstance();
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
			return null;
		}
	}

	@Test
	public void testConstructorCreateSingle() throws Exception {
		final RandomFactory<?> factory = new RandomFactory<>(this::createPojo);

		final Object pojo = factory.createSingle();
		final Object propertyValue = getPropertyValue(pojo);

		Assert.assertNotNull("Property " + this.propertyName + " was not filled correctly for " + this.pojoClass,
				propertyValue);

		if (propertyValue instanceof Number) {
			Assert.assertNotEquals("Value should be over zero on default: " + propertyValue,
					((Number) propertyValue).doubleValue(), 0.0, 0.0);
		}
	}

	@Test
	public void testConstructorCreate() throws Exception {
		final RandomFactory<?> factory = new RandomFactory<>(this::createPojo);

		for (final Object pojo : factory.create(5)) {
			final Object propertyValue = getPropertyValue(pojo);

			Assert.assertNotNull("Property " + this.propertyName + " was not filled correctly for " + this.pojoClass,
					propertyValue);

			if (propertyValue instanceof Number) {
				Assert.assertNotEquals("Value should be over zero on default: " + propertyValue,
						((Number) propertyValue).doubleValue(), 0.0, 0.0);
			}
		}
	}

}
