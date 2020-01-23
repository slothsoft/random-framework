package de.slothsoft.random;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class test that some fields are not filled on default.
 */

@RunWith(Parameterized.class)
public class RandomFactoryFieldNullTest {

	private static int classCounter;

	@Parameters(name = "{1} ({0})")
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(new Object[][]{

				data("simpleString", String.class),

		});
	}

	private static Object[] data(String propertyName, Class<?> propertyClass) throws Exception {
		return new Object[]{PojoGenerator.generate("Nono" + classCounter++, propertyName, propertyClass), propertyName};
	}

	private final Class<?> pojoClass;
	private final String propertyName;

	public RandomFactoryFieldNullTest(Class<?> pojoClass, String propertyName) {
		this.pojoClass = pojoClass;
		this.propertyName = propertyName;
	}

	@Test
	public void testForPojoCreateSingle() throws Exception {
		final RandomFactory<?> factory = RandomFactory.forClass(this.pojoClass);

		final Object pojo = factory.createSingle();
		final Object propertyValue = getPropertyValue(pojo);

		Assert.assertNull("Property " + this.propertyName + " was filled for " + this.pojoClass, propertyValue);
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

			Assert.assertNull("Property " + this.propertyName + " was filled for " + this.pojoClass, propertyValue);
		}
	}

}
