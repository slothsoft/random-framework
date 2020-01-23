package de.slothsoft.random.types;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;

public abstract class AbstractRandomFieldTest {

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

	Object getPropertyValue(Object pojo) throws Exception {
		final Field field = pojo.getClass().getDeclaredField(this.property);
		field.setAccessible(true);
		return field.get(pojo);
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
}
