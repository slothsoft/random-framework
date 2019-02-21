package de.slothsoft.random.types;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;

public abstract class AbstractRandomFieldTest<T> {

	protected static final String PROPERTY = "Value";

	protected final Object pojo;

	protected RandomField<T> randomField;
	protected RandomFactory<?> randomFactory;

	public AbstractRandomFieldTest(Object pojo) {
		this.pojo = pojo;
	}

	@Before
	public void setUp() throws Exception {
		this.randomField = createRandomField();
	}

	protected abstract RandomField<T> createRandomField();

	@Test
	public void testForClassGuess() throws Exception {
		this.randomFactory = RandomFactory.forClass(this.pojo.getClass());

		Assert.assertEquals(this.randomField.getFieldClass(),
				this.randomFactory.getRandomField(PROPERTY).getFieldClass());

		final Object createdPojo = this.randomFactory.createSingle();
		Assert.assertNotNull(getPropertyValue(createdPojo));
	}

	static Object getPropertyValue(Object pojo) throws Exception {
		final Field field = pojo.getClass().getDeclaredField(PROPERTY.toLowerCase());
		field.setAccessible(true);
		return field.get(pojo);
	}

	@Test
	public void testConstructorGuess() throws Exception {
		this.randomFactory = new RandomFactory<>(() -> this.pojo);

		Assert.assertEquals(this.randomField.getFieldClass(),
				this.randomFactory.getRandomField(PROPERTY).getFieldClass());

		final Object createdPojo = this.randomFactory.createSingle();
		Assert.assertNotNull(getPropertyValue(createdPojo));
	}

	@Test
	public void testConstructorEmptyMap() throws Exception {
		this.randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());

		Assert.assertEquals(null, this.randomFactory.getRandomField(PROPERTY));

		final Object createdPojo = this.randomFactory.createSingle();
		Assert.assertNull(getPropertyValue(createdPojo));
	}

	@Test
	public void testConstructorManual() throws Exception {
		this.randomFactory = new RandomFactory<>(() -> this.pojo, new HashMap<>());
		this.randomFactory.addRandomField(PROPERTY, this.randomField);

		Assert.assertSame(this.randomField, this.randomFactory.getRandomField(PROPERTY));

		final Object createdPojo = this.randomFactory.createSingle();
		Assert.assertNotNull(getPropertyValue(createdPojo));
	}
}
