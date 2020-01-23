package de.slothsoft.random;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

public class RandomFactoryTest {

	private static final String PROPERTY_VALUE = "value";

	public static class Pojo {

		private String value;

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	private final RandomFactory<Pojo> factory = new RandomFactory<>(Pojo::new);

	@Test
	public void testCreateSingle() throws Exception {
		final Pojo pojo = this.factory.createSingle();

		Assert.assertNotNull(pojo);
		Assert.assertNull(pojo.getValue());
	}

	@Test
	public void testCreate() throws Exception {
		for (final Pojo pojo : this.factory.create(2)) {

			Assert.assertNotNull(pojo);
			Assert.assertNull(pojo.getValue());
		}
	}

	@Test
	public void testFindRandomFieldNull() throws Exception {
		Assert.assertNull(this.factory.findRandomField(PROPERTY_VALUE));
	}

	@Test(expected = RandomException.class)
	public void testGetRandomFieldNull() throws Exception {
		Assert.assertNull(this.factory.getRandomField(PROPERTY_VALUE));
	}

	@Test
	public void testAddRandomField() throws Exception {
		final RandomField randomField = () -> UUID.randomUUID().toString();
		this.factory.addRandomField(PROPERTY_VALUE, randomField);

		Assert.assertSame(randomField, this.factory.findRandomField(PROPERTY_VALUE));
		Assert.assertSame(randomField, this.factory.getRandomField(PROPERTY_VALUE));
	}

	@Test
	public void testAddRandomFieldOverride() throws Exception {
		this.factory.addRandomField(PROPERTY_VALUE, () -> "D:");

		final RandomField randomField = () -> UUID.randomUUID().toString();
		this.factory.addRandomField(PROPERTY_VALUE, randomField);

		Assert.assertSame(randomField, this.factory.findRandomField(PROPERTY_VALUE));
	}

	@Test
	public void testAddRandomFieldUsedByCreateSingle() throws Exception {
		this.factory.addRandomField(PROPERTY_VALUE, () -> UUID.randomUUID().toString());

		final Pojo pojo = this.factory.createSingle();
		Assert.assertNotNull(pojo);
		Assert.assertNotNull(pojo.getValue());
	}

	@Test
	public void testAddRandomFieldUsedUsedByCreate() throws Exception {
		this.factory.addRandomField(PROPERTY_VALUE, () -> UUID.randomUUID().toString());

		for (final Pojo pojo : this.factory.create(3)) {
			Assert.assertNotNull(pojo);
			Assert.assertNotNull(pojo.getValue());
		}
	}

	@Test
	public void testAddRandomFieldNull() throws Exception {
		this.factory.addRandomField(PROPERTY_VALUE, null);

		Assert.assertNull(this.factory.findRandomField(PROPERTY_VALUE));
	}

	@Test
	public void testRemoveRandomField() throws Exception {
		final RandomField randomField = () -> UUID.randomUUID().toString();
		this.factory.addRandomField(PROPERTY_VALUE, randomField);
		this.factory.removeRandomField(PROPERTY_VALUE);

		Assert.assertNull(this.factory.findRandomField(PROPERTY_VALUE));
	}

}
